package io.grow2gether.redditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import io.grow2gether.redditclone.dto.SubredditDto;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public  class SubredditMapper {

//    SubredditMapper INSTANCE = Mappers.getMapper(SubredditMapper.class);

    public SubredditDto map(Subreddit subreddit){
        return SubredditDto.builder()
                .description(subreddit.getDescription())
                .name(subreddit.getName())
                .duration(TimeAgo.using(subreddit.getCreatedAt().toEpochMilli()))
                .numberOfPosts(0)
                .build();
    }

//    @InheritInverseConfiguration
//    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
//    Subreddit mapToDto(SubredditDto subredditDto, User user);
//

     public Subreddit mapToDto(SubredditDto subredditDto, User user) {
        return Subreddit.builder()
                .createdAt(java.time.Instant.now())
                .name(subredditDto.getName())
                .user(user)
                .description(subredditDto.getDescription())
                .build();
    }
}
