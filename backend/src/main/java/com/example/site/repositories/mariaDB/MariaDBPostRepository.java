package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.CategoryDAO;
import com.example.site.repositories.models.PostDAO;
import com.example.site.repositories.models.TagDAO;
import com.example.site.repositories.repos.PostRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static com.example.site.repositories.mariaDB.MariaDBPostRepository.Queries.*;

public class MariaDBPostRepository implements PostRepository
{

    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBPostRepository(TransactionTemplate template, JdbcTemplate jdbc)
    {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public PostDAO createPost(String title, String content, int user_id, int votes, int views)
    {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime now = LocalDateTime
                .now();

        return template.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                        PreparedStatement ps = con.prepareStatement(
                                INSERT_POST, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, title);
                        ps.setString(2, content);
                        ps.setInt(3, user_id);
                        ps.setInt(4, votes);
                        ps.setInt(5, views);
                        ps.setString(6, dateFormatter.format(now));
                        return ps;
                    }, keyHolder
            );
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return new PostDAO(
                    id, title, content, user_id, votes, views, dateFormatter.format(now)
            );
        });


    }

    @Override
    public PostDAO getPostById(Integer id)
    {
        return jdbc.queryForObject(GET_POST,
                (rs, rowNum) -> fromResultSet(rs), id);
    }

    @Override
    public String getUserImageUrlByUserId(Integer id)
    {
        return null;
    }

    @Override
    public String getUserNameByUserId(Integer id)
    {
        return null;
    }

    @Override
    public List<PostDAO> getPostsByUserId(Integer user_id)
    {
        return null;
    }

    @Override
    public List<PostDAO> listPosts(int page, int pageSize)
    {
        return jdbc.query(LIST_POSTS,
                (rs, rowNum) -> fromResultSet(rs), page * pageSize, pageSize);
    }

    @Override
    public void deletePost(Integer id)
    {
        jdbc.update(DELETE_POSTS, id);
    }

    @Override
    public String getUserName(int id)
    {
        return jdbc.queryForObject(GET_POST_USER_USERNAME, String.class, id);
    }

    @Override
    public String getUserImageUlr(int id)
    {
        return jdbc.queryForObject(GET_POST_USER_IMAGE_URL, String.class, id);

    }

    @Override
    public List<TagDAO> getPostTags(Integer post_id)
    {
        return jdbc.query(GET_POST_TAGS,
                (rs, rowNum) -> MariaDBTagRepository.fromResultSet(rs), post_id);
    }

    @Override
    public List<CategoryDAO> getPostCategories(Integer post_id)
    {
        return jdbc.query(GET_POST_CATEGORIES,
                (rs, rowNum) -> MariaDBCategoryRepository.fromResultSet(rs), post_id);
    }

    @Override
    public Integer getPostVotes(Integer post_id)
    {
        return jdbc.queryForObject(GET_POST_VOTES, Integer.class, post_id);
    }

    @Override
    public void addCategory(Integer post_id, Integer category_id)
    {
        jdbc.update(ADD_CATEGORY, post_id, category_id);
    }

    @Override
    public void addTag(Integer post_id, Integer tag_id)
    {
        jdbc.update(ADD_TAG, post_id, post_id);
    }

    @Override
    public List<PostDAO> getPosts()
    {
        return jdbc.query(GET_ALL_POSTS,
                (rs, rowNum) -> fromResultSet(rs));
    }


    public static PostDAO fromResultSet(ResultSet rs) throws SQLException
    {
        return new PostDAO(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getInt("user_id"),
                rs.getInt("votes"),
                rs.getInt("views"),
                rs.getString("post_date")
        );
    }


    static class Queries
    {
        public static final String INSERT_POST =
                "INSERT INTO post(title, content, user_id, votes, views, post_date) VALUES (?, ?, ?, ?, ?, ?)";
        public static final String GET_ALL_POSTS = "SELECT * FROM post";

        public static final String GET_POST =
                "select * FROM post\n" +
                        "WHERE id = ?";

        public static final String LIST_POSTS =
                "SELECT *" +
                        "FROM post u\n" +
                        "LIMIT ?, ?";
        public static final String DELETE_POSTS = "DELETE FROM post WHERE id = ?";
        public static final String GET_POST_USER_USERNAME =
                """
                        SELECT u.username
                        FROM post p JOIN user u ON u.id = p.user_id
                        WHERE p.id = ?""";
        public static final String GET_POST_USER_IMAGE_URL =
                """
                        SELECT u.image_url
                        FROM post p JOIN user u ON u.id = p.user_id
                        WHERE p.id = ?""";

        public static final String GET_POST_CATEGORIES = """
                SELECT *
                FROM post_categories
                WHERE post_id =?""";
        public static final String GET_POST_TAGS = """
                SELECT *
                FROM post_tags
                WHERE post_id = ?""";
        public static final String GET_POST_VOTES = "SELECT COUNT(*) as likes from like_post WHERE liked = ?";
        public static final String ADD_TAG = "INSERT INTO post_tags\n" +
                "VALUES (?, ?)";
        public static final String ADD_CATEGORY = "INSERT INTO post_categories\n" +
                "VALUES (?, ?)";
    }
}
