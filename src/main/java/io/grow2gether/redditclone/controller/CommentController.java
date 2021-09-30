package io.grow2gether.redditclone.controller;

import io.grow2gether.redditclone.dto.CommentRequest;
import io.grow2gether.redditclone.dto.CommentResponse;
import io.grow2gether.redditclone.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentRequest commentRequest) {
        this.commentService.save(commentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{userName}")
    ResponseEntity<List<CommentResponse>> getAllCommentsForUser(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForUser(userName));
    }
}
