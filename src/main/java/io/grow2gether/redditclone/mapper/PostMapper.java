package io.grow2gether.redditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import io.grow2gether.redditclone.dto.PostRequest;
import io.grow2gether.redditclone.dto.PostResponse;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.CommentRepository;
import io.grow2gether.redditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class PostMapper {

    private final CommentRepository commentRepository;
    private final VoteRepository voteRepository;

    public Post mapToPost(PostRequest postRequest, Subreddit subreddit, User user) {
        return Post.builder()
                .description(postRequest.getDescription())
                .postName(postRequest.getPostName())
                .subreddit(subreddit)
                .user(user)
                .createdAt(Instant.now())
                .url(postRequest.getUrl())
                .build();
    }

    public PostResponse mapToPostResponse(Post post) {
        return PostResponse.builder()
                .postId(post.getPostId())
                .description(post.getDescription())
                .postName(post.getPostName())
                .subredditName(post.getSubreddit().getName())
                .url(post.getUrl())
                .username(post.getUser().getUsername())
                .voteCount(voteCount(post))
                .CommentCount(commentCount(post))
                .duration(TimeAgo.using(post.getCreatedAt().toEpochMilli()))
                .build();
    }

    private Integer commentCount(Post post) {
        return this.commentRepository.countAllByPost(post);
    }

    private Integer voteCount(Post post) {
        return this.voteRepository.countAllByPost(post);
    }
}
