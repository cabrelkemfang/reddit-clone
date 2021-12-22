package io.grow2gether.redditclone.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueSubredditNameValidator.class)
public @interface  UniqueSubredditName {
    String message() default "Subreddit already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
