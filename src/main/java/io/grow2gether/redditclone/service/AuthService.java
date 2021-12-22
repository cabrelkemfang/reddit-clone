package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.AuthenticationResponse;
import io.grow2gether.redditclone.dto.LoginRequest;
import io.grow2gether.redditclone.dto.RegisterRequest;
import io.grow2gether.redditclone.event.UserRegistrationEvent;
import io.grow2gether.redditclone.exeption.SpringRedditException;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.model.VerificationToken;
import io.grow2gether.redditclone.repository.UserRepository;
import io.grow2gether.redditclone.repository.VerificationRepository;
import io.grow2gether.redditclone.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .username(registerRequest.getUsername())
                .enabled(false)
                .createdAt(Instant.now())
                .build();

        userRepository.save(user);
        eventPublisher.publishEvent(new UserRegistrationEvent(user));
    }


    @Transactional
    public void verifyToken(String token) {
        VerificationToken verificationToken = verificationRepository.findByToken(token).orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken);
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String userName = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new SpringRedditException("User not found with name : " + userName));
        user.setEnabled(true);
        this.userRepository.save(user);
    }


    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByUsername(principal.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
