package io.grow2gether.redditclone.event;

import io.grow2gether.redditclone.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserRegistrationEvent extends ApplicationEvent {

    private final User user;

    public UserRegistrationEvent(User user) {
        super(user);
        this.user = user;
    }
}
