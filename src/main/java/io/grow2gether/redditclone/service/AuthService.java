package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.dto.AuthenticationResponse;
import io.grow2gether.redditclone.dto.LoginRequest;
import io.grow2gether.redditclone.dto.RegisterRequest;
import io.grow2gether.redditclone.exceptions.SpringRedditException;
import io.grow2gether.redditclone.model.NotificationEmail;
import io.grow2gether.redditclone.model.User;
import io.grow2gether.redditclone.model.VerificationToken;
import io.grow2gether.redditclone.repository.UserRepository;
import io.grow2gether.redditclone.repository.VerificationTokenRepository;
import io.grow2gether.redditclone.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signup(RegisterRequest registerRequest) {

        User user = User.builder().username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .enabled(false)
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .createdAt(Instant.now())
                .build();

        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate Your Account ", user.getEmail(), "http://localhost:8080/api/auth/accountverification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid token"));
        fetchUserAndEnable(verificationToken);
    }


    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringRedditException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }

    @Transactional()
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }
}
