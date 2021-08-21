package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.SubredditDto;
import io.grow2gether.redditclone.exceptions.SpringRedditException;
import io.grow2gether.redditclone.mapper.SubredditMapper;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public SubredditDto save(SubredditDto subredditDto) {
        User user = authService.getCurrentUser();
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapToDto(subredditDto, user));
        return subredditMapper.map(subreddit);
    }


    public List<SubredditDto> getAllSubreddit() {
        return subredditRepository.findAll().stream()
                .map(subredditMapper::map)
                .collect(Collectors.toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = this.subredditRepository.findById(id).orElseThrow(() -> new SpringRedditException("Subreddit not found"));
        return subredditMapper.map(subreddit);
    }

}
