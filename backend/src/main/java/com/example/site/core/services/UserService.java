package com.example.site.core.services;

import com.example.site.core.models.Post;
import com.example.site.core.models.User;
import com.example.site.repositories.repos.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username,
                           String email,
                           String password,
                           String phone_number,
                           String image_url) {
        return Mapper.fromUserDAO(userRepository.createUser(username,
                email,
                encoder.encode(password),
                phone_number,
                image_url));
    }

    public User getUserById(int id) {
        return Mapper.fromUserDAO(userRepository.getUserById(id));
    }

    public List<User> listUsers(int page, int pageSize) {
        return userRepository.listUsers(page, pageSize)
                .stream()
                .map(Mapper::fromUserDAO)
                .collect(Collectors.toList());
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public void makeFriendWith(Integer myId, Integer friendId) {
        userRepository.makeFollow(myId, friendId);
    }

    public List<User> getFollowers(int id) {
        return userRepository.getFollowers(id)
                .stream()
                .map(Mapper::fromUserDAO)
                .collect(Collectors.toList());
    }

    public List<User> getFollowings(int id) {
        return userRepository.getFollowings(id)
                .stream()
                .map(Mapper::fromUserDAO)
                .collect(Collectors.toList());
    }

    public List<Post> getUserPosts(int id) {
        return userRepository.getUserPosts(id)
                .stream()
                .map(Mapper::fromPostDAO)
                .collect(Collectors.toList());
    }

    public void followSomebody(Integer myId, Integer otherId) {
        userRepository.makeFollow(myId, otherId);
    }

    public void unfollowSomebody(Integer myId, Integer otherId) {
        userRepository.makeUnfollow(myId, otherId);
    }
}
