package kz.baltabayev.springsecurityexample.controller;

import kz.baltabayev.springsecurityexample.model.entity.User;
import kz.baltabayev.springsecurityexample.service.AppService;
import kz.baltabayev.springsecurityexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "User is saved!";
    }
}
