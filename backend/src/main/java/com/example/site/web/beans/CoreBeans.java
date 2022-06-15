package com.example.site.web.beans;

import com.example.site.core.models.Comment;
import com.example.site.core.services.*;
import com.example.site.repositories.repos.*;
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
    public FollowshipService FriendshipService(FollowshipRepository repository){
        return new FollowshipService(
                repository);
    }

    @Bean
    public CommentService CommentService(CommentRepository repository)
    {
        return new CommentService(repository);
    }
    @Bean
    ReportService ReportService(ReportRepository repository){
        return new ReportService(repository);
    }

    @Bean
    LikeService LikeService(LikeRepository repository){
        return new LikeService(repository);
    }

}
