package io.grow2gether.redditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
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
public abstract class PostMapper {
    SubredditMapper INSTANCE = Mappers.getMapper(SubredditMapper.class);

//    @Mapping(target = "userName", source = "user.username")
//    @Mapping(target = "subredditName", source = "subreddit.name")
//    @Mapping(target = "voteCount", constant = "0")
//    @Mapping(target = "duration",expression = "java(java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

//    @InheritInverseConfiguration
//    @Mapping(target = "description", source = "postRequest.description")
//    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedAt().toEpochMilli());
    }
}
