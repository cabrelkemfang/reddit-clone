package io.grow2gether.redditclone.mapper;

import io.grow2gether.redditclone.dto.SubredditDto;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring")
public interface SubredditMapper {

    SubredditMapper INSTANCE = Mappers.getMapper(SubredditMapper.class);

    SubredditDto map(Subreddit subreddit);

    @InheritInverseConfiguration
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    Subreddit mapToDto(SubredditDto subredditDto, User user);
}
