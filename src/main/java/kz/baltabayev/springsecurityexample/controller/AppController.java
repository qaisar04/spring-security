package kz.baltabayev.springsecurityexample.controller;

import kz.baltabayev.springsecurityexample.model.Application;
import kz.baltabayev.springsecurityexample.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @GetMapping("/all-app")
    @PreAuthorize("hasRole('USER')")
    public List<Application> allApplications() {
        return appService.getApplicationList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Application getAppById(@PathVariable Long id) {
        return appService.applicationById(id);
    }
}
