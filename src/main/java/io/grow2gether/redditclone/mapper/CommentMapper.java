package io.grow2gether.redditclone.mapper;

import io.grow2gether.redditclone.dto.CommentRequest;
import io.grow2gether.redditclone.dto.CommentResponse;
import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommentMapper {

    public Comment mapToEntity(CommentRequest commentRequest, Post post, User user) {
        return Comment.builder()
                .post(post)
                .text(commentRequest.getText())
                .user(user)
                .createdAt(Instant.now())
                .build();
    }

    public static CommentResponse mapToDto(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedAt().toString())
                .userName(comment.getUser().getUsername())
                .text(comment.getText())
                .post_id(comment.getPost().getPostId())
                .build();
    }
}
