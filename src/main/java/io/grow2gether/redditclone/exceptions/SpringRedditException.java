package io.grow2gether.redditclone.exceptions;

import org.springframework.mail.MailException;

public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String exMessage, MailException e){
        super(exMessage);
    }

    public SpringRedditException(String exMessage){
        super(exMessage);
    }
}
