package io.grow2gether.redditclone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.grow2gether.redditclone.dto.PageableResult;
import io.grow2gether.redditclone.dto.SubredditRequest;
import io.grow2gether.redditclone.dto.SubredditResponse;
import io.grow2gether.redditclone.mapper.SubredditMapper;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.SubredditRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SubredditService.class})
@ExtendWith(SpringExtension.class)
class SubredditServiceTest {
    @MockBean
    private SubredditMapper subredditMapper;

    @MockBean
    private SubredditRepository subredditRepository;

    @Autowired
    private SubredditService subredditService;

    @Test
    void testSave() {
        User user = new User();
        user.setCreatedAt(null);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setUsername("janedoe");

        Subreddit subreddit = new Subreddit();
        subreddit.setCreatedAt(null);
        subreddit.setDescription("The characteristics of someone or something");
        subreddit.setId(123L);
        subreddit.setName("Name");
        subreddit.setUser(user);
        when(this.subredditRepository.save((Subreddit) any())).thenReturn(subreddit);

        User user1 = new User();
        user1.setCreatedAt(null);
        user1.setEmail("jane.doe@example.org");
        user1.setEnabled(true);
        user1.setPassword("iloveyou");
        user1.setUserId(123L);
        user1.setUsername("janedoe");

        Subreddit subreddit1 = new Subreddit();
        subreddit1.setCreatedAt(null);
        subreddit1.setDescription("The characteristics of someone or something");
        subreddit1.setId(123L);
        subreddit1.setName("Name");
        subreddit1.setUser(user1);
        when(this.subredditMapper.mapToSubreddit((SubredditRequest) any())).thenReturn(subreddit1);
        assertSame(subreddit,
                this.subredditService.save(new SubredditRequest("Name", "The characteristics of someone or something")));
        verify(this.subredditRepository).save((Subreddit) any());
        verify(this.subredditMapper).mapToSubreddit((SubredditRequest) any());
    }

    @Test
    void testGetAll() {
        ArrayList<Subreddit> subredditList = new ArrayList<>();
        when(this.subredditRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(subredditList));
        PageableResult<SubredditResponse> actualAll = this.subredditService.getAll(1, 3);
        assertEquals(subredditList, actualAll.getData());
        assertEquals(1, actualAll.getTotalPages());
        assertEquals(0L, actualAll.getTotalOfItems());
        assertEquals(3, actualAll.getSize());
        assertEquals(1, actualAll.getPage());
        verify(this.subredditRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAll2() {
        User user = new User();
        user.setCreatedAt(null);
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setPassword("iloveyou");
        user.setUserId(123L);
        user.setUsername("janedoe");

        Subreddit subreddit = new Subreddit();
        subreddit.setCreatedAt(null);
        subreddit.setDescription("The characteristics of someone or something");
        subreddit.setId(123L);
        subreddit.setName("Name");
        subreddit.setUser(user);

        ArrayList<Subreddit> subredditList = new ArrayList<>();
        subredditList.add(subreddit);
        PageImpl<Subreddit> pageImpl = new PageImpl<>(subredditList);
        when(this.subredditRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        when(this.subredditMapper.mapToSubredditResponse((Subreddit) any())).thenReturn(new SubredditResponse());
        PageableResult<SubredditResponse> actualAll = this.subredditService.getAll(1, 3);
        assertEquals(1, actualAll.getData().size());
        assertEquals(1, actualAll.getPage());
        assertEquals(1, actualAll.getTotalPages());
        assertEquals(1L, actualAll.getTotalOfItems());
        assertEquals(3, actualAll.getSize());
        verify(this.subredditRepository).findAll((org.springframework.data.domain.Pageable) any());
        verify(this.subredditMapper).mapToSubredditResponse((Subreddit) any());
    }
}

