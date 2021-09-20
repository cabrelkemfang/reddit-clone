package io.grow2gether.redditclone.dto;

import io.grow2gether.redditclone.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VoteRequest {
    private Long postId;
    private VoteType voteType;
}
