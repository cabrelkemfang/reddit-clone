package io.grow2gether.redditclone.repository;

import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
