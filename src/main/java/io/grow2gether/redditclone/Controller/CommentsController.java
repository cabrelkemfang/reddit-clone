package io.grow2gether.redditclone.Controller;

import io.grow2gether.redditclone.dto.CommentRequest;
import io.grow2gether.redditclone.dto.CommentResponse;
import io.grow2gether.redditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentRequest commentRequest) {
        commentService.createComment(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post Created Successfully");
    }

    @GetMapping("by-post/{postId}")
    public List<CommentResponse> getAllCommentByPost(@PathVariable Long postId) {
        return (List<CommentResponse>) ResponseEntity.status(HttpStatus.OK).body(this.commentService.getAllCommentByPost(postId));
    }

    @GetMapping("by-user/{userName}")
    public ResponseEntity<List<CommentResponse>> getAllCommentByUser(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.commentService.getAllCommentByUser(userName));
    }
}
