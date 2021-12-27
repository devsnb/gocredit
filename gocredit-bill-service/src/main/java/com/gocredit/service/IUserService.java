package com.gocredit.service;

import com.gocredit.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE")
public interface IUserService {

    @PostMapping("/user-api/users")
    public ResponseEntity<User> signup(@RequestBody User user);

    @PostMapping("/user-api/users/login/email/{email}/password/{password}")
    public ResponseEntity<User> loginWithEmail(@PathVariable("email") String email, @PathVariable("password") String password);
}
