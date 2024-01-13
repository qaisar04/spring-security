package kz.baltabayev.springsecurityexample.service;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import kz.baltabayev.springsecurityexample.model.Application;
import kz.baltabayev.springsecurityexample.model.entity.User;
import kz.baltabayev.springsecurityexample.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.LongStream;

@Service
@Getter
@RequiredArgsConstructor
public class AppService {

    private List<Application> applicationList;

    @PostConstruct
    public void loadAppInDB() {
        Faker faker = new Faker();
        applicationList = LongStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();
    }

    public Application applicationById(Long id) {
        return applicationList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst().orElse(null);
    }
}
