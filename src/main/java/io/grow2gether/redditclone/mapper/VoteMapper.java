package io.grow2gether.redditclone.mapper;

import io.grow2gether.redditclone.dto.VoteRequest;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.model.Vote;
import org.springframework.stereotype.Service;

@Service
public class VoteMapper {

    public Vote mapToEntity(VoteRequest voteRequest, Post post, User user) {
        return Vote.builder()
                .post(post)
                .user(user)
                .voteType(voteRequest.getVoteType())
                .build();
    }
}
