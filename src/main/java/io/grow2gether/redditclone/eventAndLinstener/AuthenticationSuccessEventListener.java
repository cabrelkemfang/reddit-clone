package io.grow2gether.redditclone.eventAndLinstener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationSuccessEventListener {

    @EventListener({AuthenticationSuccessEvent.class})
    public void onAuthenticationSuccess(AuthenticationSuccessEvent authorizedEvent) {
    }
}
