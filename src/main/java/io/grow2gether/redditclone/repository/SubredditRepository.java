package io.grow2gether.redditclone.repository;

import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
    Optional<Subreddit> findByName(String name);

    Optional<Subreddit> findAllByUserAndName(User user, String name);
}
