package com.example.site.repositories.mariaDB;

import com.example.site.repositories.models.CategoryDAO;
import com.example.site.repositories.models.LikeDAO;
import com.example.site.repositories.models.PostDAO;
import com.example.site.repositories.repos.CategoryRepository;
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

import static com.example.site.repositories.mariaDB.MariaDBPostRepository.Queries.INSERT_POST;

public class MariaDBCategoryRepository implements CategoryRepository {

    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBCategoryRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public CategoryDAO createCategory(String name) {
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
            return new CategoryDAO(
                    id, name
            );
        });
    }

    @Override
    public List<CategoryDAO> getAllCategories() {
      return   jdbc.query(Queries.GET_CATEGORIES,
                (rs, rowNum) -> fromResultSet(rs));
    }

    public static CategoryDAO fromResultSet(ResultSet rs) throws SQLException {
        return new CategoryDAO(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    static class Queries {
        public static final String CREATE_CATEGORY = "INSERT INTO categories(name)\n" +
                "VALUES (?)";
        public static final String GET_CATEGORIES = "SELECT * FROM categories ";
    }


}

