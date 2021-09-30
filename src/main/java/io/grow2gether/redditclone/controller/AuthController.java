package io.grow2gether.redditclone.controller;

import io.grow2gether.redditclone.dto.AuthenticationResponse;
import io.grow2gether.redditclone.dto.DataResponse;
import io.grow2gether.redditclone.dto.LoginRequest;
import io.grow2gether.redditclone.dto.RegisterRequest;
import io.grow2gether.redditclone.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public DataResponse<Void> signup(@RequestBody RegisterRequest registerRequest) {
        return authService.signup(registerRequest);
    }

    @GetMapping("/accountverification/{token}")
    public DataResponse<Void> verifyAccount(@PathVariable String token) {
        return authService.verifyAccount(token);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
