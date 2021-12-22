package io.grow2gether.redditclone.mapper;

import io.grow2gether.redditclone.dto.CommentRequest;
import io.grow2gether.redditclone.dto.CommentResponse;
import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CommentMapper {

    public Comment mapToComment(CommentRequest commentRequest, Post post, User user) {
        return Comment.builder()
                .post(post)
                .user(user)
                .text(commentRequest.getComment())
                .createdAt(Instant.now())
                .build();
    }

    public CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getCommentId())
                .comment(comment.getText())
                .postName(comment.getPost().getPostName())
                .userName(comment.getUser().getUsername())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
