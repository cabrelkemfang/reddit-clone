package io.grow2gether.redditclone.mapper;

import io.grow2gether.redditclone.dto.SubredditRequest;
import io.grow2gether.redditclone.dto.SubredditResponse;
import io.grow2gether.redditclone.model.Subreddit;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SubredditMapper {

    public Subreddit mapToSubreddit(SubredditRequest subredditRequest) {
        return Subreddit.builder()
                .name(subredditRequest.getName())
                .description(subredditRequest.getDescription())
                .createdAt(Instant.now())
                .build();
    }

    public SubredditResponse mapToSubredditResponse(Subreddit subreddit) {
        return SubredditResponse.builder()
                .name(subreddit.getName())
                .description(subreddit.getDescription())
                .createdAt(subreddit.getCreatedAt())
                .id(subreddit.getId())
                .build();
    }
}
