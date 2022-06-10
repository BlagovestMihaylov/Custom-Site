package com.example.site.repositories.mariaDB;

import com.example.site.core.models.Friendship;
import com.example.site.repositories.repos.FriendshipRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

public class MariaDBFriendshipRepository implements FriendshipRepository {
    private final TransactionTemplate template;
    private final JdbcTemplate jdbc;

    public MariaDBFriendshipRepository(TransactionTemplate template, JdbcTemplate jdbc) {
        this.template = template;
        this.jdbc = jdbc;
    }

    @Override
    public Friendship createFriendship(Integer id1, Integer id2) {
        return null;
    }
}
