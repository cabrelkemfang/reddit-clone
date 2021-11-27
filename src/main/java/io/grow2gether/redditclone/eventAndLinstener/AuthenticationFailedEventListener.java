package io.grow2gether.redditclone.eventAndLinstener;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailedEventListener {

    @EventListener(AuthenticationFailureBadCredentialsEvent.class)
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
    }
}
