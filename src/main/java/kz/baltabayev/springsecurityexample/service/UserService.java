package kz.baltabayev.springsecurityexample.service;

import kz.baltabayev.springsecurityexample.config.UserDetailsConfig;
import kz.baltabayev.springsecurityexample.mapper.UserMapper;
import kz.baltabayev.springsecurityexample.model.dto.AuthRequest;
import kz.baltabayev.springsecurityexample.model.dto.UserRequest;
import kz.baltabayev.springsecurityexample.model.entity.User;
import kz.baltabayev.springsecurityexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    public void addUser(UserRequest userRequest) {
        User user = userMapper.toModel(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean auth(AuthRequest authRequest) {
        //todo
       return false;
    }
}
