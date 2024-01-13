package kz.baltabayev.springsecurityexample.controller;

import kz.baltabayev.springsecurityexample.model.dto.AuthRequest;
import kz.baltabayev.springsecurityexample.model.dto.UserRequest;
import kz.baltabayev.springsecurityexample.model.entity.User;
import kz.baltabayev.springsecurityexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<String> addUser(
            @RequestBody UserRequest userRequest
    ) {
        userService.addUser(userRequest);
        return ResponseEntity.ok("User is saved!");
    }

    @PostMapping
    ResponseEntity<?> auth(
            @RequestBody AuthRequest authRequest
    ) {
        return ResponseEntity
                .ok(userService.auth(authRequest));
    }
}
