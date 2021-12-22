package io.grow2gether.redditclone.validation;

import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.repository.SubredditRepository;
import io.grow2gether.redditclone.service.AuthService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueSubredditNameValidator implements ConstraintValidator<UniqueSubredditName, String> {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Subreddit subreddit = this.subredditRepository.findAllByUserAndName(authService.getCurrentUser(), name).orElse(null);
        return name != null && subreddit == null;
    }
}
