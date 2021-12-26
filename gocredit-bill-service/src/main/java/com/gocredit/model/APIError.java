package com.gocredit.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class APIError {
    LocalDateTime timestamp;
    HttpStatus status;
    String message;
    String path;
}
