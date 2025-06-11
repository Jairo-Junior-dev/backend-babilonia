package com.finance.babilonia.config.handle.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginException extends RuntimeException {

    private int status;
    private String message;

    public LoginException(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
