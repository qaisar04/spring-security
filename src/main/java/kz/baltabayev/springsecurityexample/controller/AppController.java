package kz.baltabayev.springsecurityexample.controller;

import kz.baltabayev.springsecurityexample.model.Application;
import kz.baltabayev.springsecurityexample.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/app")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Application> allApplications() {
        return appService.getApplicationList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public Application getAppById(@PathVariable Long id) {
        return appService.applicationById(id);
    }

    @GetMapping("/greetings")
    public ResponseEntity<Map<String, String>> getGreetingsV3(Principal principal) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("greeting", "hello, %s"
                        .formatted(principal.getName())));
    }
}
