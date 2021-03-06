package io.grow2gether.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDto {
    private String name;
    private String description;
    private String duration;
    private Integer numberOfPosts;
}
