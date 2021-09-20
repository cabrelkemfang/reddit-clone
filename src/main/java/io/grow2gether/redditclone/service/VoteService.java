package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.VoteRequest;
import io.grow2gether.redditclone.exceptions.SpringRedditException;
import io.grow2gether.redditclone.mapper.VoteMapper;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.model.Vote;
import io.grow2gether.redditclone.model.VoteType;
import io.grow2gether.redditclone.repository.PostRepository;
import io.grow2gether.redditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final VoteMapper voteMapper;

    @Transactional
    public void vote(VoteRequest voteRequest) {
        Post post = getPostById(voteRequest.getPostId());
        User currentUser = this.authService.getCurrentUser();
        Optional<Vote> vote = this.voteRepository.findTopByUserAndPostOrderByVoteIdDesc(currentUser, post);
        if (vote.isPresent() && (vote.get().getVoteType().equals(voteRequest.getVoteType()))) {
            throw new SpringRedditException("You have already " + vote.get().getVoteType().name() + " This post");
        } else {
//            post.setVoteCount(post.getVoteCount() + 1);
            if (voteRequest.getVoteType().equals(VoteType.UPVOTE)) {
                post.setVoteCount(post.getVoteCount() + 1);
            } else {
                post.setVoteCount(post.getVoteCount() - 1);
            }
        }
        this.voteRepository.save(voteMapper.mapToEntity(voteRequest, post, currentUser));
        this.postRepository.save(post);
    }

    private Post getPostById(long postId) {
        return this.postRepository.findById(postId).orElseThrow(() -> new SpringRedditException("Post Not Found"));
    }
}
