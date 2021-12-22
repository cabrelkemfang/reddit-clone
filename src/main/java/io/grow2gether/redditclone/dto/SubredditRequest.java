package io.grow2gether.redditclone.dto;

import io.grow2gether.redditclone.validation.UniqueSubredditName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditRequest {
    @NotEmpty(message = "The subreddit name is required")
    @UniqueSubredditName
    private String name;
    @NotEmpty(message = "The description is required")
    private String description;
}
