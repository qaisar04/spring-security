package kz.baltabayev.springsecurityexample.service;

import kz.baltabayev.springsecurityexample.exception.InvalidCredentialsException;
import kz.baltabayev.springsecurityexample.exception.PasswordMismatchException;
import kz.baltabayev.springsecurityexample.exception.UserAlreadyExistsException;
import kz.baltabayev.springsecurityexample.mapper.UserMapper;
import kz.baltabayev.springsecurityexample.model.dto.AuthRequest;
import kz.baltabayev.springsecurityexample.model.dto.TokenResponse;
import kz.baltabayev.springsecurityexample.model.dto.UserRequest;
import kz.baltabayev.springsecurityexample.model.entity.User;
import kz.baltabayev.springsecurityexample.model.types.Role;
import kz.baltabayev.springsecurityexample.repository.UserRepository;
import kz.baltabayev.springsecurityexample.security.CustomUserDetailsService;
import kz.baltabayev.springsecurityexample.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;

    public void register(UserRequest userRequest) {
        validatePasswordConfirmation(userRequest.getPassword(), userRequest.getConfirmPassword());

        if (findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("The user with the specified username exists.");
        }

        User user = userMapper.toModel(userRequest);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public TokenResponse authenticate(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new TokenResponse(token);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePasswordConfirmation(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Passwords don't match.");
        }
    }
}
