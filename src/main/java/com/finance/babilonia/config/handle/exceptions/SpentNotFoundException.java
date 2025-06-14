package com.finance.babilonia.config.handle.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpentNotFoundException extends RuntimeException {
    private  int status;
    private String message;

    public SpentNotFoundException(int status ,String message) {
        this.status = status;
        this.message = message;
    }
}
