package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.PostDAO;
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
            String password, String phone_number, String image_url) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        // String image_ulr = "https://cdn.marica.bg/images/marica.bg/857/991-ratio-preslava.jpg";

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
                ps.setString(6, image_url);
                return ps;
            }, keyHolder);
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

            return new UserDAO(id, username, email, password, phone_number, dateFormatter.format(now), image_url);
        });
    }


    @Override
    public UserDAO getUserById(Integer id) {
        return jdbc.queryForObject(GET_USER,
                (rs, rowNum) -> fromResultSet(rs), id);

    }

    @Override
    public UserDAO getUserByUsername(String userName) {
        return jdbc.queryForObject(GET_USER_BY_USERNAME,
                (rs, rowNum) -> fromResultSet(rs), userName);
    }

    @Override
    public List<UserDAO> listUsers(int page, int pageSize) {
        return jdbc.query(LIST_USERS,
                (rs, rowNum) -> fromResultSet(rs), page * pageSize, pageSize);
    }

    @Override
    public List<UserDAO> getUsers() {
        return jdbc.query(GET_ALL_USERS, (rs, rowNum) -> fromResultSet(rs));
    }

    @Override
    public void deleteUser(Integer id) {
        jdbc.update(DELETE_USER, id);
    }

    @Override
    public void makeFollow(Integer myId, Integer friendId) {
        jdbc.update(FOLLOW_SOMEBODY, myId, friendId);
    }

    @Override
    public void makeUnfollow(Integer myId, Integer friendId) {
        jdbc.update(UNFOLLOW_SOMEBODY, myId, friendId);
    }


    @Override
    public List<UserDAO> getFollowers(int id) {
        return jdbc.query(GET_USER_FOLLOWERS,
                (rs, rowNumber) -> fromResultSet(rs), id);
    }

    @Override
    public List<UserDAO> getFollowings(int id) {
        return jdbc.query(GET_USER_FOLOWINGS,
                (rs, rowNumber) -> fromResultSet(rs), id);
    }

    @Override
    public List<PostDAO> getUserPosts(int id) {
        return jdbc.query(GET_USER_POSTS,
                (rs, rowNumber) -> MariaDBPostRepository.fromResultSet(rs), id);
    }


    private UserDAO fromResultSet(ResultSet rs) throws SQLException {
        return new UserDAO(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phone_number"),
                rs.getString("registration_date"),
                rs.getString("image_url")
        );
    }

    static class Queries {

        public static final String GET_ALL_USERS = "SELECT * FROM user";
        public static final String INSERT_USER =
                "INSERT INTO user (username, email, password, phone_number, registration_date, image_url)\n"
                        + "VALUES (?, ?, ?, ?, ?, ?)";
        public static final String GET_USER =
                """
                        SELECT *
                        FROM user u
                        WHERE u.id = ?""";

        public static final String GET_USER_BY_USERNAME =
                """
                        SELECT *
                                        FROM user
                                        WHERE username = ?""";
        public static final String LIST_USERS =
                """
                        SELECT *
                        FROM user u
                        LIMIT ?, ?""";

        public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

        public static final String GET_USER_FOLLOWERS =
                """
                        SELECT *
                        FROM follow f
                        JOIN user u ON u.id = f.user_id1
                        WHERE user_id2 = ?""";

        public static final String GET_USER_FOLOWINGS = """
                SELECT *
                FROM follow f
                JOIN user u ON u.id = f.user_id2
                WHERE user_id1 = ?""";

        public static final String GET_USER_POSTS = """
                SELECT post.*
                FROM post JOIN user u ON u.id = post.user_id
                WHERE user_id = ?""";

        public static final String FOLLOW_SOMEBODY =
                """
                        INSERT INTO follow(user_id1, user_id2)
                        VALUES (?, ?);
                        """;
        public static final String UNFOLLOW_SOMEBODY =
                """
                        DELETE
                        FROM follow
                        WHERE user_id1 = ?
                          AND user_id2 = ?""";
    }


}
