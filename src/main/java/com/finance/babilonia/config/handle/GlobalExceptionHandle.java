package com.finance.babilonia.config.handle;

import com.finance.babilonia.config.handle.exceptions.LoginException;
import com.finance.babilonia.config.handle.exceptions.SpentFoundException;
import com.finance.babilonia.config.handle.exceptions.SpentNotFoundException;
import com.finance.babilonia.controller.response.ErroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandle {


    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErroResponse> LoginExceptionHandle(LoginException loginException) {
        return ResponseEntity.status(loginException.getStatus()).body(
                new ErroResponse(loginException.getMessage(), "username ou email ja existem")
        );
    }

    @ExceptionHandler(SpentNotFoundException.class)
    public ResponseEntity<ErroResponse> SpentNotFoundException(SpentNotFoundException exception ){
        return ResponseEntity.status(exception.getStatus()).body(new ErroResponse(exception.getMessage() ,"Spent not found"));
    }

    @ExceptionHandler(SpentFoundException.class)
    public ResponseEntity<ErroResponse> SpentFoundException(SpentFoundException exception ){
        return ResponseEntity.status(exception.getStatus()).body(new ErroResponse(exception.getMessage() ,"Spent exists"));
    }


}
