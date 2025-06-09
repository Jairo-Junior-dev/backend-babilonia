package com.finance.babilonia.controller;

import com.finance.babilonia.controller.request.EmailRequest;
import com.finance.babilonia.controller.request.UserRequest;
import com.finance.babilonia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("add")
    public ResponseEntity responseEntity(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("change-password")
    public ResponseEntity changePassword(@RequestBody EmailRequest email) {
        userService.changePassword(email.email());
        return ResponseEntity.ok().build();
    }
}
