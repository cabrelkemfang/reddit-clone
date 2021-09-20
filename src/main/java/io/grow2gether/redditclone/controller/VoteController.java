package io.grow2gether.redditclone.controller;

import io.grow2gether.redditclone.dto.VoteRequest;
import io.grow2gether.redditclone.model.Vote;
import io.grow2gether.redditclone.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@AllArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteRequest voteRequest) {
        this.voteService.vote(voteRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
