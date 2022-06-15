package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.ReportDAO;
import com.example.site.repositories.repos.ReportRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.security.Key;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static com.example.site.repositories.mariaDB.MariaDBCommentRepository.Queries.GET_POST_COMMENTS;
import static com.example.site.repositories.mariaDB.MariaDBReportRepository.Queries.CREATE_REPORT;
import static com.example.site.repositories.mariaDB.MariaDBReportRepository.Queries.GET_POST_REPORTS;

public class MariaDBReportRepository implements ReportRepository {
    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBReportRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }


    @Override
    public ReportDAO createReport(Integer user_id, Integer post_id, Integer comment_id, String content) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return template.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        CREATE_REPORT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, user_id);
                ps.setObject(2, post_id);
                ps.setObject(3, comment_id);
                ps.setString(4, content);
                return ps;
            }, keyHolder);
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

            return new ReportDAO(id, user_id, post_id, comment_id, content, dateFormatter.format(now));
        });
    }

    @Override
    public List<ReportDAO> getUserReports(Integer user_id) {
        return jdbc.query(Queries.GET_USER_REPORTS,
                (rs, rowNum) -> fromResultSet(rs), user_id);
    }

    @Override
    public List<ReportDAO> getPostReports(Integer post_id) {
        return jdbc.query(GET_POST_REPORTS,
                (rs, rowNum) -> fromResultSet(rs), post_id);
    }

    @Override
    public List<ReportDAO> getCommentReports(Integer comment_id) {
        return jdbc.query(Queries.GET_COMMENT_REPORTS,
                (rs, rowNum) -> fromResultSet(rs), comment_id);
    }


    private ReportDAO fromResultSet(ResultSet rs) throws SQLException {
        return new ReportDAO(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("post_id"),
                rs.getInt("comment_id"),
                rs.getString("content"),
                rs.getString("report_date")
        );
    }

    static class Queries {
        public static final String CREATE_REPORT =
                "INSERT INTO report(user_id, post_id, comment_id, content)\n" +
                        "VALUES (?, ?, ?, ?) ";
        public static final String GET_USER_REPORTS = """
                SELECT *
                FROM report
                WHERE user_id = ?""";
        public static final String GET_POST_REPORTS = """
                SELECT *
                FROM report
                WHERE post_id = ?
                """;
        public static final String GET_COMMENT_REPORTS = """
                SELECT *
                FROM report
                WHERE comment_id = ?
                """;

    }


}
