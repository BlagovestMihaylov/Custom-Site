package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.LikeDAO;
import com.example.site.repositories.repos.LikeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.site.repositories.mariaDB.MariaDBLikeRepository.Queries.*;

public class MariaDBLikeRepository implements LikeRepository {
    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBLikeRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public LikeDAO likeComment(Integer user_id, Integer liked) {
        return getLikeDAO(user_id, liked, LIKE_COMMENT);
    }

    @Override
    public LikeDAO likePost(Integer user_id, Integer liked) {
        return getLikeDAO(user_id, liked, LIKE_POST);

    }

    private LikeDAO getLikeDAO(Integer user_id, Integer liked, String like) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return template.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        like, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, user_id);
                ps.setInt(2, liked);
                return ps;
            }, keyHolder);

            return new LikeDAO(user_id, liked, dateFormatter.format(now));
        });
    }

    @Override
    public List<LikeDAO> getPostLikes(Integer post_id) {
        return jdbc.query(GET_POST_LIKES,
                (rs, rowNum) -> fromResultSet(rs), post_id);
    }

//    @Override
//    public List<LikeDAO> getUserLikes(Integer user_id) {
//        return jdbc.query(GET_USER_LIKES,
//                (rs, rowNum) -> fromResultSet(rs), user_id);
//    }

    @Override
    public List<LikeDAO> getCommentLikes(Integer comment_id) {
        return jdbc.query(Queries.GET_COMMENT_LIKES,
                (rs, rowNum) -> fromResultSet(rs), comment_id);
    }

    private LikeDAO fromResultSet(ResultSet rs) throws SQLException {
        return new LikeDAO(
                rs.getInt("user_id"),
                rs.getInt("liked"),
                rs.getString("like_date")
        );
    }

    static class Queries {
        public static final String LIKE_COMMENT =
                "INSERT INTO like_comment(user_id, liked)\n" +
                        "VALUES (?, ?) ";
        public static final String LIKE_POST =
                "INSERT INTO like_post (user_id, liked)\n" +
                        "VALUES (?, ?) ";
        public static final String GET_POST_LIKES = "SELECT * from like_post WHERE liked = ?";
        public static final String GET_COMMENT_LIKES = "SELECT * from like_comment WHERE liked = ?";
        //public static final String GET_USER_LIKES = "SELECT * from likes WHERE user_id = ?";
    }

}
