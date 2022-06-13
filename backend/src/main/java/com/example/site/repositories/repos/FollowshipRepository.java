package com.example.site.repositories.repos;

import com.example.site.core.models.Followship;

public interface FollowshipRepository {

    Followship createFriendship(Integer id1, Integer id2);
}
