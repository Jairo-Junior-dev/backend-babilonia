package com.finance.babilonia.config.handle.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpentFoundException extends RuntimeException {
    private  int status;
    private String message;

    public SpentFoundException(int status , String message) {
        this.status = status;
        this.message = message;
    }
}
