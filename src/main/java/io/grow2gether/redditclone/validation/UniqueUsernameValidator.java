package io.grow2gether.redditclone.validation;

import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        User user = this.userRepository.findByUsername(username).orElse(null);

        return username != null && user == null;
    }
}
