package io.grow2gether.redditclone.repository;

import io.grow2gether.redditclone.model.Post;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.model.Vote;
import io.grow2gether.redditclone.model.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByUserAndPostOrderByVoteIdDesc(User user, Post post);

    Integer countByPostAndVoteType(Post post, VoteType voteType);
}
