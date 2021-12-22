package io.grow2gether.redditclone.repository;

import io.grow2gether.redditclone.model.Comment;
import io.grow2gether.redditclone.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
