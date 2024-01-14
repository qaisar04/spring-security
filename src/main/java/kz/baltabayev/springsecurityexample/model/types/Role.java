package kz.baltabayev.springsecurityexample.model.types;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum Role {
    USER,
    ADMIN
}