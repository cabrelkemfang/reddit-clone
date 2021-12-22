package io.grow2gether.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private Long postId;
    private String postName;
    private String subredditName;
    private String url;
    private String description;
    private Integer voteCount;
    private String username;
    private Integer CommentCount;
    private String duration;
}
