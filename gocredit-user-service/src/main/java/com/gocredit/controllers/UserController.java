package com.gocredit.controllers;

import com.gocredit.exceptions.BillNotFoundException;
import com.gocredit.exceptions.CreditCardNotFoundException;
import com.gocredit.exceptions.UserNotFoundException;
import com.gocredit.model.User;
import com.gocredit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-api")
@Profile("dev")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/users")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    public User loginWithEmail(String email, String password) {
        return null;
    }

    public User loginWithContactNumber(Long contactNumber, String password) {
        return null;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return null;
    }

    @DeleteMapping("/users/id/{userId}")
    public void deleteUser(@PathVariable("userId") int deleteId) {

        userService.deleteUser(deleteId);
    }

    @GetMapping("/users/id/{userId}")
    public ResponseEntity<User> getById(@PathVariable("userId") int userId) {
        User user = userService.getById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/role/{role}")
    public ResponseEntity<List<User>> getByRole(@PathVariable("role") String role) {
        return ResponseEntity.ok(userService.getByRole(role));
    }

    @GetMapping("users/name/{name}")
    public ResponseEntity<List<User>> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(userService.getByName(name));
    }

    @GetMapping("users/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @GetMapping("users/contactnumber/{contactnumbers}")
    public ResponseEntity<User> getByContactNumber(@PathVariable("contactnumber") long contactNumber) {
        return ResponseEntity.ok(userService.getByContactNumber(contactNumber));
    }

    public ResponseEntity<User> getByCardNumber(String cardNumber) throws CreditCardNotFoundException{
        return null;
    }

    public User getByBillId(int billId) throws BillNotFoundException{
        return null;
    }

    public List<User> getByIsBillPaid(boolean isPaid) throws UserNotFoundException{
        return null;
    }


}
