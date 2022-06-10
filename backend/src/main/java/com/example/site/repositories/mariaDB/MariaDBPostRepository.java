package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.PostDAO;
import com.example.site.repositories.repos.PostRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import static com.example.site.repositories.mariaDB.MariaDBPostRepository.Queries.*;

public class MariaDBPostRepository implements PostRepository {

    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBPostRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public PostDAO createPost(String title, String content, int user_id, int votes, int views) {
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
                        return ps;
                    }, keyHolder
            );
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return new PostDAO(
                    id, title, content, user_id, votes, views
            );
        });


    }

    @Override
    public PostDAO getPostById(Integer id) {
        return jdbc.queryForObject(GET_POST,
                (rs, rowNum) -> fromResultSet(rs), id);
    }

    @Override
    public String getUserImageUrlByUserId(Integer id) {
        return null;
    }

    @Override
    public String getUserNameByUserId(Integer id) {
        return null;
    }

    @Override
    public List<PostDAO> getPostsByUserId(Integer user_id) {
        return null;
    }

    @Override
    public List<PostDAO> listPosts(int page, int pageSize) {
        return jdbc.query(LIST_POSTS,
                (rs, rowNum) -> fromResultSet(rs), page * pageSize, pageSize);
    }

    @Override
    public void deletePost(Integer id) {
        jdbc.update(DELETE_POSTS, id);
    }

    private PostDAO fromResultSet(ResultSet rs) throws SQLException {
        return new PostDAO(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getInt("user_id"),
                rs.getInt("votes"),
                rs.getInt("views")
        );
    }


    static class Queries {
        public static final String INSERT_POST =
                "INSERT INTO post(title, content, user_id, votes, views) VALUES (?, ?, ?, ?, ?)";

        public static final String GET_POST =
                "select * FROM post\n" +
                        "WHERE id = ?";

        public static final String LIST_POSTS =
                "SELECT *" +
                        "FROM post u\n" +
                        "LIMIT ?, ?";
        public static final String DELETE_POSTS = "DELETE FROM post WHERE id = ?";
    }
}
