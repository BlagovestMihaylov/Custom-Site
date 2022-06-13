package com.example.site.repositories.mariaDB;

import com.example.site.core.models.Followship;
import com.example.site.repositories.repos.FollowshipRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

public class MariaDBFollowshipRepository implements FollowshipRepository {
    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBFollowshipRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public Followship createFriendship(Integer id1, Integer id2) {
        return null;
    }

    static class Queries {
        public static final String MAKE_FOLLOW = "";
    }

}
