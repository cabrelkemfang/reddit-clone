package io.grow2gether.redditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import io.grow2gether.redditclone.dto.PostRequest;
import io.grow2gether.redditclone.dto.PostResponse;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.model.VoteType;
import io.grow2gether.redditclone.repository.CommentRepository;
import io.grow2gether.redditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class PostMapper {
    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;

    public PostResponse mapToDto(Post post) {
        return PostResponse.builder()
                .post_id(post.getPostId())
                .description(post.getDescription())
                .postName(post.getPostName())
                .url(post.getUrl())
                .downVote(voteRepository.countByPostAndVoteType(post, VoteType.DOWNVOTE))
                .upVote(voteRepository.countByPostAndVoteType(post, VoteType.UPVOTE))
                .voteCount(post.getVoteCount())
                .commentCount(commentRepository.countByPost(post))
                .userName(post.getUser().getUsername())
//                .duration(TimeAgo.using(post.getCreatedAt().toEpochMilli()))
                .subredditName(post.getSubreddit().getName())
                .build();
    }


    public Post map(PostRequest postRequest, Subreddit subreddit, User user) {

        return Post.builder()
                .description(postRequest.getDescription())
                .postName(postRequest.getPostName())
                .subreddit(subreddit)
                .createdAt(Instant.now())
                .voteCount(0)
                .user(user)
                .url(postRequest.getUrl())
                .build();
    }

}
