package io.grow2gether.redditclone.repository;

import io.grow2gether.redditclone.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldCreateUser() {
        User user = User.builder()
                .email("ghislaincabrel.kemfang23777@gmail.com")
                .enabled(false)
                .userId(56L)
                .username("ghislain555")
                .createdAt(Instant.now())
                .password("ghislaincabrel766")
                .build();

        userRepository.save(user);

        Optional<User> user1 = userRepository.findAllByEmail("ghislaincabrel.kemfang23777@gmail.com");

        log.info(String.valueOf(user1.get()));
    }

}