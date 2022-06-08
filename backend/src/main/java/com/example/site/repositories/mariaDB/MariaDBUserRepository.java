package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.UserDAO;
import com.example.site.repositories.repos.UserRepository;
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

import static com.example.site.repositories.mariaDB.MariaDBUserRepository.Queries.*;

public class MariaDBUserRepository implements UserRepository {
    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBUserRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    public UserDAO createUser(
            String username, String email,
            String password, String phone_number) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return template.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, phone_number);
                ps.setString(5, dateFormatter.format(now));
                return ps;
            }, keyHolder);
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return new UserDAO(id, username, email, password,phone_number,dateFormatter.format(now));
        });
    }

    @Override
    public UserDAO getUserById(Integer id) {
        return jdbc.queryForObject(GET_USER,
                (rs, rowNum) -> fromResultSet(rs), id);

    }

    @Override
    public List<UserDAO> listUsers(int page, int pageSize) {
        return jdbc.query(LIST_USERS,
                (rs, rowNum) -> fromResultSet(rs), page * pageSize, pageSize);
    }

    @Override
    public void deleteUser(Integer id) {
        jdbc.update(DELETE_USER, id);
    }


    private UserDAO fromResultSet(ResultSet rs) throws SQLException {
        return new UserDAO(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phone_number"),
                rs.getString("registration_date")
        );
    }

    static class Queries {
        public static final String INSERT_USER =
                "INSERT INTO user (username, email, password, phone_number, registration_date) VALUES (?, ?, ?, ?, ?)";
        public static final String GET_USER =
                "SELECT *"+
                        "FROM user u\n" +
                        "WHERE u.id = ?";

        public static final String LIST_USERS =
                "SELECT *"+
                        "FROM user u\n" +
                        "LIMIT ?, ?";

        public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
    }
}
