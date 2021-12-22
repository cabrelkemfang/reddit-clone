package io.grow2gether.redditclone.dto;

import io.grow2gether.redditclone.validation.UniqueEmail;
import io.grow2gether.redditclone.validation.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    @UniqueEmail
    private String email;
    @NotEmpty(message = "Username is required")
    @UniqueUsername
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
}
