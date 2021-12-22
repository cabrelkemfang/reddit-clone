package io.grow2gether.redditclone.util;

import io.grow2gether.redditclone.model.Post;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

public class PostFactory {

    private PostFactory() {
        super();
    }

    public static Post createRandomPost() {
        return Post.builder()
                .postId(Long.valueOf(RandomStringUtils.randomAlphanumeric(252)))
                .postName(RandomStringUtils.randomAlphanumeric(10))
                .description(RandomStringUtils.randomAlphanumeric(32))
                .url(RandomStringUtils.randomAlphanumeric(15))
                .createdAt(Instant.now())
                .build();
    }
}
