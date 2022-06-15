package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.CommentDAO;
import com.example.site.repositories.repos.CommentRepository;
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
import java.util.Objects;

import static com.example.site.repositories.mariaDB.MariaDBCommentRepository.Queries.*;

public class MariaDBCommentRepository implements CommentRepository {
    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBCommentRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public CommentDAO createComment(Integer user_id, Integer post_id, Integer comment_id, String content, Integer votes, String time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return template.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        INSERT_COMMENT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, user_id);
                ps.setObject(2, post_id);
                ps.setObject(3, comment_id);
                ps.setString(4, content);
                return ps;
            }, keyHolder);
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return new CommentDAO(id,
                    user_id,
                    post_id,
                    comment_id,
                    content,
                    0,
                    dateFormatter.format(now));
        });
    }

    @Override
    public List<CommentDAO> getPostComments(int post_id) {

        return jdbc.query(GET_POST_COMMENTS,
                (rs, rowNum) -> fromResultSet(rs), post_id);
    }

    @Override
    public List<CommentDAO> getCommentComments(int comment_id) {
        return jdbc.query(GET_COMMENT_COMMENTS,
                (rs, rowNum) -> fromResultSet(rs), comment_id);
    }


    private CommentDAO fromResultSet(ResultSet rs) throws SQLException {
        return new CommentDAO(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("post_id"),
                rs.getInt("comment_id"),
                rs.getString("content"),
                rs.getInt("votes"),
                rs.getString("comment_date")
        );
    }


    public static class Queries {
        public static final String INSERT_COMMENT =
                "INSERT INTO comment(user_id, post_id, comment_id, content)\n" +
                        "VALUES (?, ?, ?, ?)";
        public static final String GET_POST_COMMENTS = """
                SELECT *
                FROM comment
                WHERE post_id = ?""";

        public static final String GET_COMMENT_COMMENTS = """
                SELECT *
                FROM comment
                WHERE comment_id = ?""";
    }
}
