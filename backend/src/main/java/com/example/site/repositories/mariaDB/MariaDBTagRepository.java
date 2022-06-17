package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.TagDAO;
import com.example.site.repositories.repos.TagRepository;
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

public class MariaDBTagRepository implements TagRepository {

    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBTagRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public TagDAO createTag(String name) {
        return template.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(con -> {
                        PreparedStatement ps = con.prepareStatement(
                                Queries.CREATE_CATEGORY, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, name);
                        return ps;
                    }, keyHolder
            );
            Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();
            return new TagDAO(
                    id, name
            );
        });
    }

    @Override
    public List<TagDAO> getAllTags() {
        return   jdbc.query(Queries.GET_TAG,
                (rs, rowNum) -> fromResultSet(rs));
    }

    public static TagDAO fromResultSet(ResultSet rs) throws SQLException {
        return new TagDAO(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    static class Queries {
        public static final String CREATE_CATEGORY = "INSERT INTO tags(name)\n" +
                "VALUES (?)";
        public static final String GET_TAG = "SELECT * FROM tags ";
    }

}
