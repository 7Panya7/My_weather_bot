package ru.telegram.usersdb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Не найден пользователь")
public class UsersNotFoundException extends RuntimeException{

    public UsersNotFoundException() {
        super();
    }

    public UsersNotFoundException(String message) {
        super(message);
    }

    public UsersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UsersNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
