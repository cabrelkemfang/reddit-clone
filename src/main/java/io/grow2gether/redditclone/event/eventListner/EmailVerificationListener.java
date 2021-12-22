package io.grow2gether.redditclone.event.eventListner;

import io.grow2gether.redditclone.dto.NotificationEmail;
import io.grow2gether.redditclone.event.UserRegistrationEvent;
import io.grow2gether.redditclone.exeption.SpringRedditException;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.model.VerificationToken;
import io.grow2gether.redditclone.repository.VerificationRepository;
import io.grow2gether.redditclone.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.grow2gether.redditclone.util.Constants.ACTIVATION_EMAIL;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    private final MailService mailService;
    private final VerificationRepository verificationRepository;

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        log.info("------ Email even -------");
        String token = generateVerificationToken(event.getUser());

        mailService.sendMail(new NotificationEmail(event.getUser().getEmail(), "Please Activate your account", "Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .build();
        verificationRepository.save(verificationToken);
        return token;
    }


}
