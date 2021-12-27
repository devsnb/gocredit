package com.gocredit.service;

import com.gocredit.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface IUserService {

    @GetMapping("/user-api/users/id/{userId}")
    public ResponseEntity<User> getById(@PathVariable("userId") int userId);

    @PostMapping("/user-api/users")
    public ResponseEntity<User> signup(@RequestBody User user);

}
