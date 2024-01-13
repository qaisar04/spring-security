package kz.baltabayev.springsecurityexample.model.dto;

public record AuthRequest(String username,
                          String password) {
}
