package io.grow2gether.redditclone.repository;

import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Integer countAllByPost(Post post);
}
