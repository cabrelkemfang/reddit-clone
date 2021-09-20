package io.grow2gether.redditclone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String exMessage, MailException e){
        super(exMessage);
    }

    public SpringRedditException(String exMessage){
        super(exMessage);
    }
}
