package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.DataResponse;
import io.grow2gether.redditclone.dto.PageableResult;
import io.grow2gether.redditclone.dto.SubredditDto;
import io.grow2gether.redditclone.exceptions.SpringRedditException;
import io.grow2gether.redditclone.mapper.SubredditMapper;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    private final AuthService authService;


    @Transactional
    public DataResponse<Void> save(SubredditDto subredditDto) {
        subredditRepository.save(subredditMapper.mapToEntity(subredditDto, authService.getCurrentUser()));
        return new DataResponse<>("Subreddit Created Successfully", HttpStatus.CREATED.value());
    }


    public PageableResult<SubredditDto> getAllSubreddit(int page, int size) {
        Page<Subreddit> subreddit = subredditRepository.findAll(PageRequest.of(page - 1, size));

        return new PageableResult<>(page,
                size,
                subreddit.getTotalElements(),
                subreddit.getTotalPages(),
                subreddit.getContent().stream().map(subredditMapper::mapToDto).collect(Collectors.toList()));
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = this.subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("Subreddit not found"));
        return subredditMapper.mapToDto(subreddit);
    }

}
