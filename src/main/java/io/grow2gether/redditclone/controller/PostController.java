package io.grow2gether.redditclone.controller;

import io.grow2gether.redditclone.dto.DataResponse;
import io.grow2gether.redditclone.dto.PageableResult;
import io.grow2gether.redditclone.dto.PostRequest;
import io.grow2gether.redditclone.dto.PostResponse;
import io.grow2gether.redditclone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public DataResponse<Void> createdPost(@RequestBody PostRequest postRequest) {
        return postService.save(postRequest);
    }

    @GetMapping
    public PageableResult<PostResponse> getAllPost(@RequestParam(required = false, defaultValue = "1") int page,
                                                   @RequestParam(required = false, defaultValue = "10") int size) {
        return this.postService.getAllPosts(page, size);
    }

    @GetMapping("/{id}")
    public DataResponse<PostResponse> getPost(@PathVariable Long id) {
        return this.postService.getPost(id);
    }

    @GetMapping("/by-subreddit/{id}")
    public PageableResult<PostResponse> getPostBySubreddit(@RequestParam(required = false, defaultValue = "1") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int size,
                                                           @PathVariable Long id) {
        return this.postService.getPostBySubreddit(page, size, id);

    }

}
