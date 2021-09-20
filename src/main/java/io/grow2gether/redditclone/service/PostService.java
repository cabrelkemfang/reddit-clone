package io.grow2gether.redditclone.service;


import io.grow2gether.redditclone.dto.DataResponse;
import io.grow2gether.redditclone.dto.PageableResult;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public DataResponse<Void> save(PostRequest postRequest) {
        Subreddit subreddit = this.subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SpringRedditException("Subreddit not found"));

        this.postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
        return new DataResponse<>("Post Created Successfully", HttpStatus.CREATED.value());
    }

    public DataResponse<PostResponse> getPost(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new SpringRedditException("Post Not Fount"));
        return new DataResponse<>("Post Load Successfully", HttpStatus.OK.value(), postMapper.mapToDto(post));
    }

    public PageableResult<PostResponse> getAllPosts(int page, int size) {
        Page<Post> posts = this.postRepository.findAll(PageRequest.of(page - 1, size));
        return new PageableResult<>(page,
                size,
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.getContent().stream().map(postMapper::mapToDto).collect(Collectors.toList()));
    }

    public List<PostResponse> getPostByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User Not Found"));

        return this.postRepository.findAllByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public PageableResult<PostResponse> getPostBySubreddit(int page, int size, Long subredditId) {
        Subreddit subreddit = this.subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SpringRedditException("Subreddit not found"));

        Page<Post> posts = this.postRepository.findAllBySubreddit(subreddit, PageRequest.of(page - 1, size));

        return new PageableResult<>(page,
                size,
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.getContent().stream().map(postMapper::mapToDto).collect(Collectors.toList()));
    }
}
