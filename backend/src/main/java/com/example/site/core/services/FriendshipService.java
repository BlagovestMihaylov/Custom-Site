package com.example.site.core.services;

import com.example.site.core.models.Friendship;
import com.example.site.core.models.User;
import com.example.site.repositories.repos.FriendshipRepository;

import java.util.List;

public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private UserService userService;

    public FriendshipService(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    public Friendship createFriendship(Integer id1, Integer id2)
    {
        return Mapper.fromFriendshipDAO(friendshipRepository.createFriendship(id1, id2));
    }

//    public List<User> getFriends(Integer id)
//    {
//        return
//    }


}
