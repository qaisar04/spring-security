package kz.baltabayev.springsecurityexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    private Long id;
    private String name;
    private String author;
    private String version;
}
