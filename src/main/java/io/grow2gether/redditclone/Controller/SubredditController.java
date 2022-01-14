package io.grow2gether.redditclone.Controller;


import io.grow2gether.redditclone.dto.DataResponse;
import io.grow2gether.redditclone.dto.PageableResult;
import io.grow2gether.redditclone.dto.SubredditRequest;
import io.grow2gether.redditclone.dto.SubredditResponse;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public DataResponse<Subreddit> createSubreddit(@Valid @RequestBody SubredditRequest subredditRequest) {
        subredditService.save(subredditRequest);
        return new DataResponse<>("Subreddit created successfully", HttpStatus.CREATED.value());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageableResult<SubredditResponse> getAllSubreddit(@RequestParam(required = false, defaultValue = "1") int page,
                                                             @RequestParam(required = false, defaultValue = "10") int size) {
        return subredditService.getAll(page, size);
    }


}
