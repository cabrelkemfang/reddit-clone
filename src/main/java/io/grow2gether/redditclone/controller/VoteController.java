package io.grow2gether.redditclone.controller;

import io.grow2gether.redditclone.dto.DataResponse;
import io.grow2gether.redditclone.dto.VoteRequest;
import io.grow2gether.redditclone.model.Vote;
import io.grow2gether.redditclone.service.VoteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public DataResponse<Void> vote(@RequestBody VoteRequest voteRequest) {
        return this.voteService.vote(voteRequest);
    }

}
