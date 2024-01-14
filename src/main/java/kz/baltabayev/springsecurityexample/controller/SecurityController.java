package kz.baltabayev.springsecurityexample.controller;

import kz.baltabayev.springsecurityexample.exception.ApplicationError;
import kz.baltabayev.springsecurityexample.model.dto.AuthRequest;
import kz.baltabayev.springsecurityexample.model.dto.TokenResponse;
import kz.baltabayev.springsecurityexample.model.dto.UserRequest;
import kz.baltabayev.springsecurityexample.security.CustomUserDetailsService;
import kz.baltabayev.springsecurityexample.service.UserService;
import kz.baltabayev.springsecurityexample.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        userService.register(userRequest);
        return ResponseEntity
                .ok("User is saved!");
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ApplicationError.builder()
                            .status(HttpStatus.UNAUTHORIZED.value())
                            .message("Invalid username or password")
                            .build());
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
        String token = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(token));
    }
}
