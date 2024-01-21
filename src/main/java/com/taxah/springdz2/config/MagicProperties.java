package com.taxah.springdz2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * MagicProperties Class
 * <p>
 * This class is a Spring component that uses the @ConfigurationProperties annotation
 * to bind external configuration properties with the specified prefix ("myapp") to fields.
 * It includes fields representing various endpoints for a web application.
 * <p>
 * Dependencies:
 * - @Component: Indicates that this class is a Spring-managed component.
 * - @Data: Lombok annotation to automatically generate getter, setter, toString, etc.
 * - @ConfigurationProperties: Binds external configuration properties with the specified prefix.
 * <p>
 * Properties:
 * - is are SQL queries for UserRepository Class
 */
@Component
@Data
@ConfigurationProperties(prefix = "myapp")
public class MagicProperties {
    String findAll;
    String save;
    String deleteById;
    String getOne;
    String update;
}
