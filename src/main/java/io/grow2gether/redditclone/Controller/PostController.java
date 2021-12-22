package io.grow2gether.redditclone.Controller;

import io.grow2gether.redditclone.dto.PostRequest;
import io.grow2gether.redditclone.dto.PostResponse;
import io.grow2gether.redditclone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody @Valid  PostRequest postRequest) {
        postService.createPost(postRequest);
        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId));
    }

    @GetMapping("by-subreddit/{subredditId}")
    public ResponseEntity<List<PostResponse>> getPostBySubreddit(@PathVariable Long subredditId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPostBySubreddit(subredditId));
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> getAllPostByUserName(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postService.getAllPostsByUsername(username));
    }
}
