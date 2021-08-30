package io.grow2gether.redditclone.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import io.grow2gether.redditclone.dto.PostRequest;
import io.grow2gether.redditclone.dto.PostResponse;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import org.mapstruct.Mapper;

import java.time.Instant;


@Mapper(componentModel = "spring")
public class PostMapper {

    public PostResponse mapToDto(Post post) {
        return PostResponse.builder()
                .postName(post.getPostName())
                .description(post.getDescription())
                .subredditName(post.getSubreddit().getName())
                .userName(post.getUser().getUsername())
                .voteCount(0)
                .duration(TimeAgo.using(post.getCreatedAt().toEpochMilli()))
                .build();
    }

    public Post map(PostRequest postRequest, Subreddit subreddit, User user) {
      return   Post.builder()
                .postName(postRequest.getPostName())
                .description(postRequest.getDescription())
                .postName(postRequest.getPostName())
                .createdAt(Instant.now())
                .subreddit(subreddit)
                .user(user)
                .build();
    }

}
