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
public class SubredditResponse {
    private long id;
    private String name;
    private String description;
    private Instant createdAt;

}
