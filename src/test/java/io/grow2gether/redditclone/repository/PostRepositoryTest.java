package io.grow2gether.redditclone.repository;

import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.util.PostFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;


@SpringBootTest
@Slf4j
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("Should create a post")
    public void shouldCreatePost() {

        User user = User.builder()
                .email("ghislaincabrel.g@gmail.com")
                .enabled(false)
                .userId(556L)
                .username("ghislain")
                .createdAt(Instant.now())
                .password("ghislaincabrel7")
                .build();

        Subreddit subreddit = Subreddit.builder()
                .createdAt(Instant.now())
                .id(306L)
                .description("description")
                .name("name")
                .user(user)
                .build();

        Post post = Post.builder()
                .postName("post 1")
                .postId(202L)
                .description("description")
                .createdAt(Instant.now())
                .url("Https://google.com")
                .user(user)
                .subreddit(subreddit)
                .build();

        postRepository.save(post);

        List<Post> posts = postRepository.findByUser(user);
        log.info(String.valueOf(posts));
    }
}