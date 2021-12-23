package com.gocredit.repository;

import com.gocredit.exceptions.UserNotFoundException;
import com.gocredit.model.Role;
import com.gocredit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    List<User> findByRole(Role role) throws UserNotFoundException;

    List<User> findByName(String name) throws UserNotFoundException;

    List<User> findByEmail(String email) throws UserNotFoundException;

    List<User> findByContactNumber(long contactNumber) throws UserNotFoundException;
}
