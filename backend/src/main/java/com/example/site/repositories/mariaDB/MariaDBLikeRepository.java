package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.LikeDAO;
import com.example.site.repositories.models.ReportDAO;
import com.example.site.repositories.repos.LikeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static com.example.site.repositories.mariaDB.MariaDBLikeRepository.Queries.GET_POST_LIKES;
import static com.example.site.repositories.mariaDB.MariaDBLikeRepository.Queries.GET_USER_LIKES;
import static com.example.site.repositories.mariaDB.MariaDBReportRepository.Queries.CREATE_REPORT;

public class MariaDBLikeRepository implements LikeRepository {
    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBLikeRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public LikeDAO createLike(Integer user_id, Integer post_id, Integer comment_id) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return template.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        Queries.CREATE_LIKE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, user_id);
                ps.setObject(2, post_id);
                ps.setObject(3, comment_id);
                return ps;
            }, keyHolder);
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

            return new LikeDAO(id, user_id, post_id, comment_id, dateFormatter.format(now));
        });

    }

    @Override
    public List<LikeDAO> getPostLikes(Integer post_id) {
        return jdbc.query(GET_POST_LIKES,
                (rs, rowNum) -> fromResultSet(rs), post_id);
    }

    @Override
    public List<LikeDAO> getUserLikes(Integer user_id) {
        return jdbc.query(GET_USER_LIKES,
                (rs, rowNum) -> fromResultSet(rs), user_id);
    }

    @Override
    public List<LikeDAO> getCommentLikes(Integer comment_id) {
        return jdbc.query(Queries.GET_COMMENT_LIKES,
                (rs, rowNum) -> fromResultSet(rs), comment_id);
    }

    private LikeDAO fromResultSet(ResultSet rs) throws SQLException {
        return new LikeDAO(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("post_id"),
                rs.getInt("comment_id"),
                rs.getString("like_date")
        );
    }

    static class Queries {
        public static final String CREATE_LIKE =
                "INSERT INTO likes(user_id, post_id, comment_id)\n" +
                        "VALUES (?, ?, ?) ";
        public static final String GET_POST_LIKES = "SELECT * from likes WHERE post_id = ?";
        public static final String GET_COMMENT_LIKES = "SELECT * from likes WHERE comment_id = ?";
        public static final String GET_USER_LIKES = "SELECT * from likes WHERE user_id = ?";
    }

}
