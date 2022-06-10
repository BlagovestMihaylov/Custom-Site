package com.example.site.web.beans;

import com.example.site.core.services.FriendshipService;
import com.example.site.core.services.PostService;
import com.example.site.core.services.UserService;
import com.example.site.repositories.repos.FriendshipRepository;
import com.example.site.repositories.repos.PostRepository;
import com.example.site.repositories.repos.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreBeans {

    @Bean
    public UserService UserService(UserRepository repository) {
        return new UserService(repository);
    }

    @Bean
    public PostService PostService(PostRepository repository)
    {
        return new PostService(repository);
    }

    @Bean
    public FriendshipService FriendshipService(FriendshipRepository repository){
        return new FriendshipService(
                repository);
    }

}
