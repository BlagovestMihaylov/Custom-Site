package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.ReportDAO;
import com.example.site.repositories.repos.ReportRepository;
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

import static com.example.site.repositories.mariaDB.MariaDBReportRepository.Queries.*;

public class MariaDBReportRepository implements ReportRepository {
    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBReportRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }


    @Override
    public ReportDAO reportComment(Integer user_id, Integer reported, String content) {
        return getReportDAO(user_id, reported, content, REPORT_COMMENT);
    }
    @Override
    public ReportDAO reportPost(Integer user_id, Integer reported, String content) {
        return getReportDAO(user_id, reported, content, REPORT_POST);
    }
    @Override
    public ReportDAO reportUser(Integer user_id, Integer reported, String content) {
        return getReportDAO(user_id, reported, content, REPORT_USER);
    }

    private ReportDAO getReportDAO(Integer user_id, Integer reported, String content, String who) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return template.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        who, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, user_id);
                ps.setInt(2, reported);
                ps.setString(3, content);
                return ps;
            }, keyHolder);
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

            return new ReportDAO(id, user_id, reported, content, dateFormatter.format(now));
        });
    }

//    @Override
//    public List<ReportDAO> getUserReports(Integer user_id) {
//        return jdbc.query(Queries.GET_USER_REPORTS,
//                (rs, rowNum) -> fromResultSet(rs), user_id);
//    }

    @Override
    public List<ReportDAO> getReportedPosts(Integer post_id) {
        return jdbc.query(GET_REPORTED_POSTS,
                (rs, rowNum) -> fromResultSet(rs), post_id);
    }

    @Override
    public List<ReportDAO> getReportedComments(Integer comment_id) {
        return jdbc.query(Queries.GET_REPORTED_COMMENTS,
                (rs, rowNum) -> fromResultSet(rs), comment_id);
    }
    @Override
    public List<ReportDAO> getReportedUsers(Integer comment_id) {
        return jdbc.query(GET_REPORTED_USERS,
                (rs, rowNum) -> fromResultSet(rs), comment_id);
    }


    private ReportDAO fromResultSet(ResultSet rs) throws SQLException {
        return new ReportDAO(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("reported"),
                rs.getString("content"),
                rs.getString("report_date")
        );
    }

    static class Queries {
        public static final String REPORT_COMMENT =
                "INSERT INTO report_comment(user_id,reported, content)\n" +
                        "VALUES (?, ?, ?) ";
        public static final String REPORT_USER =
                "INSERT INTO report_user(user_id,reported, content)\n" +
                        "VALUES (?, ?, ?) ";
        public static final String REPORT_POST =
                "INSERT INTO report_post(user_id,reported, content)\n" +
                        "VALUES (?, ?, ?) ";
//        public static final String GET_USER_REPORTS = """
//                SELECT *
//                FROM report
//                WHERE user_id = ?""";
        public static final String GET_REPORTED_POSTS = """
                SELECT *
                FROM report_post
                WHERE reported = ?
                """;
        public static final String GET_REPORTED_COMMENTS = """
                SELECT *
                FROM report_comment
                WHERE reported = ?
                """;
        public static final String GET_REPORTED_USERS = """
                SELECT *
                FROM report_user
                WHERE reported = ?
                """;

    }


}
