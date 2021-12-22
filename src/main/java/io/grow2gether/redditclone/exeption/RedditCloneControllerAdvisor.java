package io.grow2gether.redditclone.exeption;


import io.grow2gether.redditclone.dto.ErrorValidatorDetail;
import io.grow2gether.redditclone.dto.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class RedditCloneControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SpringRedditException.class)
    public ResponseEntity<ErrorValidatorDetail> handleResourceNotFoundException(SpringRedditException resourceNotFoundException,
                                                                                HttpServletRequest httpServletRequest) {
        ErrorValidatorDetail errorDetails = ErrorValidatorDetail.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(resourceNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException messageNotReadableException,
                                                                  HttpHeaders httpHeaders,
                                                                  HttpStatus httpStatus,
                                                                  WebRequest webRequest) {

        ErrorValidatorDetail errorDetails = ErrorValidatorDetail.builder()
                .status(httpStatus.value())
                .message(messageNotReadableException.getMessage())
                .build();

        return handleExceptionInternal(messageNotReadableException, errorDetails, httpHeaders, httpStatus, webRequest);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException notValidException,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {

            ErrorValidatorDetail errorValidatorDetail = new ErrorValidatorDetail();

        errorValidatorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorValidatorDetail.setMessage("Input Validation");

        List<FieldError> fieldErrorList = notValidException.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrorList) {
            List<ValidationError> validationErrorList = errorValidatorDetail.getErrors().get(fieldError.getField());
            if (validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorValidatorDetail.getErrors().put(fieldError.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fieldError.getCode());
            validationError.setMessage(fieldError.getDefaultMessage());
            validationErrorList.add(validationError);
        }

        return handleExceptionInternal(notValidException, errorValidatorDetail, headers, status, request);
    }
}
