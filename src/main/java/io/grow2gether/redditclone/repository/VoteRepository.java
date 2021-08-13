package io.grow2gether.redditclone.repository;

import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
