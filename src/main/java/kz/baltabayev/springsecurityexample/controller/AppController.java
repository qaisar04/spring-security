package kz.baltabayev.springsecurityexample.controller;

import jakarta.servlet.http.HttpServletRequest;
import kz.baltabayev.springsecurityexample.model.Application;
import kz.baltabayev.springsecurityexample.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/apps")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @GetMapping("/all-app")
    @PreAuthorize("hasRole('USER')")
    public List<Application> allApplications() {
        return appService.getApplicationList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER ')")
    public Application getAppById(@PathVariable Long id) {
        return appService.applicationById(id);
    }

    @GetMapping("/v1/greetings")
    public ResponseEntity<Map<String, String>> getGreetingsV1() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("greeting", "hello, %s"
                        .formatted(userDetails.getUsername())));
    }

    @GetMapping("/v2/greetings")
    public ResponseEntity<Map<String, String>> getGreetingsV2(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) ((Authentication) request.getUserPrincipal())
                .getPrincipal();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("greeting", "hello, %s"
                        .formatted(userDetails.getUsername())));
    }

    @GetMapping("/v3/greetings")
    public ResponseEntity<Map<String, String>> getGreetingsV3(Principal principal) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("greeting", "hello, %s"
                        .formatted(principal.getName())));
    }
}
