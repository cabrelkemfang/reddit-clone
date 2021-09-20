package io.grow2gether.redditclone.controller;

import io.grow2gether.redditclone.dto.DataResponse;
import io.grow2gether.redditclone.dto.PageableResult;
import io.grow2gether.redditclone.dto.SubredditDto;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping
    public DataResponse<Void> createSubreddit(@RequestBody SubredditDto subredditDto) {
        return subredditService.save(subredditDto);
    }

    @GetMapping
    public PageableResult<SubredditDto> getAllSubreddit(@RequestParam(required = false, defaultValue = "1") int page,
                                                        @RequestParam(required = false, defaultValue = "10") int size) {
        return subredditService.getAllSubreddit(page, size);
    }

    @GetMapping("/{id}")
    public DataResponse<SubredditDto> getSubreddit(@PathVariable Long id) {
        return new DataResponse<>("Subreddit", HttpStatus.OK.value(), this.subredditService.getSubreddit(id));
    }

}
