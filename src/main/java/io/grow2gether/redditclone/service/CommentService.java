package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.CommentRequest;
import io.grow2gether.redditclone.repository.CommentRepository;
import io.grow2gether.redditclone.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;

    public void save(CommentRequest commentRequest) {

    }

}
