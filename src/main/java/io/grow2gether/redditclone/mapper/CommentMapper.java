package io.grow2gether.redditclone.mapper;

import io.grow2gether.redditclone.dto.CommentRequest;

import io.grow2gether.redditclone.dto.CommentResponse;
import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

//    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
//
////    @Mapping(target = "post_id", expression = "java(comment.getPost().getPostId())")
//    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
//    CommentResponse mapToDto(Comment comment);
//
//    @InheritInverseConfiguration
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "text", source = "commentRequest.text")
//    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
//    Comment map(CommentRequest commentRequest, Post post, User user);

}
