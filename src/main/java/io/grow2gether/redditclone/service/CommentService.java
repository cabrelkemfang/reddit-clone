package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.CommentRequest;
import io.grow2gether.redditclone.dto.CommentResponse;
import io.grow2gether.redditclone.exceptions.SpringRedditException;
import io.grow2gether.redditclone.mapper.CommentMapper;
import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.NotificationEmail;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.CommentRepository;
import io.grow2gether.redditclone.repository.PostRepository;
import io.grow2gether.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final MailService mailService;


    @Transactional
    public void save(CommentRequest commentRequest) {
        Post post = getPostById(commentRequest.getPostId());
        this.commentRepository.save(commentMapper.mapToEntity(commentRequest, post, this.authService.getCurrentUser()));
        sendCommentNotification(this.authService.getCurrentUser().getUsername() + " posted a comment on you post:" + post.getUrl(), post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        this.mailService.sendMail(new NotificationEmail(this.authService.getCurrentUser().getUsername() + "Commented On You Post", user.getEmail(), message));
    }

    public List<CommentResponse> getAllCommentsForPost(long postId) {
        Post post = getPostById(postId);
        return this.commentRepository.findByPost(post).stream()
                .map(CommentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentResponse> getAllCommentsForUser(String userName) {
        User user = this.userRepository.findByUsername(userName).orElseThrow(() -> new SpringRedditException("User Not Found"));
        return this.commentRepository.findAllByUser(user).stream()
                .map(CommentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private Post getPostById(long postId) {
        return this.postRepository.findById(postId).orElseThrow(() -> new SpringRedditException("Post Not Found"));
    }
}

