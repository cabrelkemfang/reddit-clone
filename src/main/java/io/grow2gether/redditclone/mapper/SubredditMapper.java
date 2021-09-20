package io.grow2gether.redditclone.mapper;


import com.github.marlonlom.utilities.timeago.TimeAgo;
import io.grow2gether.redditclone.dto.SubredditDto;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import org.springframework.stereotype.Service;

@Service
public class SubredditMapper {

    public SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder()
                .description(subreddit.getDescription())
                .name(subreddit.getName())
                .numberOfPosts(0)
                .build();
    }

    public Subreddit mapToEntity(SubredditDto subredditDto, User user) {
        return Subreddit.builder()
                .createdAt(java.time.Instant.now())
                .name(subredditDto.getName())
                .user(user)
                .description(subredditDto.getDescription())
                .build();
    }
}
