package kz.baltabayev.springsecurityexample.model.dto;

import kz.baltabayev.springsecurityexample.model.type.Role;

public record UserRequest(String username,
                          String password,
                          Role role) {
}
