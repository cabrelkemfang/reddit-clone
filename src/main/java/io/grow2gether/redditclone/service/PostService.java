package io.grow2gether.redditclone.service;


import io.grow2gether.redditclone.dto.PostRequest;
import io.grow2gether.redditclone.dto.PostResponse;
import io.grow2gether.redditclone.exceptions.SpringRedditException;
import io.grow2gether.redditclone.mapper.PostMapper;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.PostRepository;
import io.grow2gether.redditclone.repository.SubredditRepository;
import io.grow2gether.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    private final SubredditRepository subredditRepository;
    private AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post save(PostRequest postRequest) {
        Subreddit subreddit = this.subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SpringRedditException("Subreddit not found"));

        User user = authService.getCurrentUser();
        log.info(subreddit.toString());
        log.info(postMapper.map(postRequest, subreddit, user).toString());
        return this.postRepository.save(postMapper.map(postRequest, subreddit, user));
    }

    public PostResponse getPost(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new SpringRedditException("Post Not Fount"));
        return postMapper.mapToDto(post);
    }

    public List<PostResponse> getAllPost() {
        return this.postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User Not Found"));

        return this.postRepository.findAllByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostBySubreddit(Long subredditId) {
        Subreddit subreddit = this.subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SpringRedditException("Subreddit not found"));

        return this.postRepository.findAllBySubreddit(subreddit).stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
