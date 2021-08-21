package io.grow2gether.redditclone.mapper;

import io.grow2gether.redditclone.dto.PostRequest;
import io.grow2gether.redditclone.dto.PostResponse;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface PostMapper {
    SubredditMapper INSTANCE = Mappers.getMapper(SubredditMapper.class);
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @InheritInverseConfiguration
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "subredditName", source = "subreddit.name")
    PostResponse mapToDto(Post post);
}
