package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.CommentRequest;
import io.grow2gether.redditclone.dto.CommentResponse;
import io.grow2gether.redditclone.exeption.SpringRedditException;
import io.grow2gether.redditclone.mapper.CommentMapper;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.CommentRepository;
import io.grow2gether.redditclone.repository.PostRepository;
import io.grow2gether.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void createComment(CommentRequest commentRequest) {
        Post post = this.postRepository.findById(commentRequest.getPostId())
                .orElseThrow(() -> new SpringRedditException("Post not Found"));
        User user = authService.getCurrentUser();
        this.commentRepository.save(commentMapper.mapToComment(commentRequest, post, user));
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getAllCommentByPost(Long postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new SpringRedditException("Post not Found"));

        List<CommentResponse> commentResponses = this.commentRepository.findAllByPost(post).stream()
                .map(commentMapper::mapToCommentResponse)
                .collect(Collectors.toList());
        return commentResponses;
    }

    public List<CommentResponse> getAllCommentByUser(String userName) {
        User user = this.userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return commentRepository.findAllByUser(user).stream()
                .map(commentMapper::mapToCommentResponse)
                .collect(Collectors.toList());
    }
}
