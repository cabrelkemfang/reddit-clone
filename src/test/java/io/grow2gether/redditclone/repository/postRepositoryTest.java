//package io.grow2gether.redditclone.repository;
//
//import io.grow2gether.redditclone.model.Post;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.time.Instant;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class postRepositoryTest {
//
//    @Container
//    MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
//            .withDatabaseName("spring-reddit-test-db")
//            .withUsername("testuser")
//            .withPassword("pass");
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Test
//    public void shouldSavePost() {
//        Post expectedPostObject = new Post(null, "First Post", "http://url.site", "Test",
//                Instant.now(), 0, null, null);
//        Post actualPostObject = postRepository.save(expectedPostObject);
//        assertThat(actualPostObject).usingRecursiveComparison()
//                .ignoringFields("postId").isEqualTo(expectedPostObject);
//    }
//}
