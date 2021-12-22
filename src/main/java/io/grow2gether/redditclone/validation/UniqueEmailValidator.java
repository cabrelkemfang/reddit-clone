package io.grow2gether.redditclone.validation;

import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        User user = this.userRepository.findAllByEmail(email).orElse(null);
        return email != null && user == null;
    }
}
