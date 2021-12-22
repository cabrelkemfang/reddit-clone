package io.grow2gether.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    @NotEmpty(message = "The post name is required")
    private String postName;
    @NotEmpty(message = "The subreddit name is required")
    private String subredditName;
    @NotEmpty(message = "The url is required")
    private String url;
    @NotEmpty(message = "The description is required")
    private String description;
}
