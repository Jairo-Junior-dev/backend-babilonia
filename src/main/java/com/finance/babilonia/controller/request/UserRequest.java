package com.finance.babilonia.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequest {
    private String username;
    private String password;
    private String email;
}
