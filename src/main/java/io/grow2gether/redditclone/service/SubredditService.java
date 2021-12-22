package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.PageableResult;
import io.grow2gether.redditclone.dto.SubredditRequest;
import io.grow2gether.redditclone.dto.SubredditResponse;
import io.grow2gether.redditclone.mapper.SubredditMapper;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public Subreddit save(SubredditRequest subredditRequest) {
        return subredditRepository.save(subredditMapper.mapToSubreddit(subredditRequest));
    }


    @Transactional(readOnly = true)
    public PageableResult<SubredditResponse> getAll(int page, int size) {
        Page<Subreddit> subreddits = this.subredditRepository.findAll(PageRequest.of(page - 1, size));

        return PageableResult.<SubredditResponse>builder()
                .page(page)
                .size(size)
                .data(subreddits.getContent().stream()
                        .map(subredditMapper::mapToSubredditResponse)
                        .collect(Collectors.toList()))
                .totalOfItems(subreddits.getTotalElements())
                .totalPages(subreddits.getTotalPages())
                .build();
    }
}
