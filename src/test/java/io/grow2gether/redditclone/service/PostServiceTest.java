package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.PostResponse;
import io.grow2gether.redditclone.mapper.PostMapper;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.repository.PostRepository;
import io.grow2gether.redditclone.repository.SubredditRepository;
import io.grow2gether.redditclone.repository.UserRepository;
import io.grow2gether.redditclone.util.PostFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private SubredditRepository subredditRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthService authService;
    @Mock
    private PostMapper postMapper;

    @Test
    @DisplayName("Should find post by id")
    void shouldFindPostById() {
        PostService postService = new PostService(postRepository, subredditRepository, postMapper, authService, userRepository);

        final Post post = PostFactory.createRandomPost();
        Mockito.when(postRepository.findById(123L)).thenReturn(Optional.of(post));

        PostResponse actualPostResponse = postService.getPost(123L);

//        Assert.assertNotNull(actualPostResponse);
//        Assert.assertEquals(actualPostResponse.getPostId(), post.getPostId());
        Assertions.assertThat(actualPostResponse.getPostName()).isEqualTo(post.getPostName());
    }
}