package com.example.site.repositories.repos;

import com.example.site.core.models.Friendship;

public interface FriendshipRepository {

    Friendship createFriendship(Integer id1, Integer id2);
}
