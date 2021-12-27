package com.gocredit.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiErrors {
    LocalDate timestamp;
    HttpStatus status;
    String message;
    String path;
}
