package com.example.site.core.services;

import com.example.site.core.models.Followship;
import com.example.site.repositories.repos.FollowshipRepository;

public class FollowshipService {
    private final FollowshipRepository followshipRepository;
    private UserService userService;

    public FollowshipService(FollowshipRepository followshipRepository) {
        this.followshipRepository = followshipRepository;
    }

    public Followship createFriendship(Integer id1, Integer id2)
    {
        return Mapper.fromFriendshipDAO(followshipRepository.createFriendship(id1, id2));
    }

//    public List<User> getFriends(Integer id)
//    {
//        return
//    }


}
