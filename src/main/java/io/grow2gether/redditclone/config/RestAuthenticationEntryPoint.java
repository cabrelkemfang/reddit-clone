package io.grow2gether.redditclone.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.grow2gether.redditclone.dto.ErrorValidatorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {

        ErrorValidatorDetail errorDetails = ErrorValidatorDetail.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build();
        response.setContentType("application/json");

//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, errorDetails);
        out.flush();
    }
}
