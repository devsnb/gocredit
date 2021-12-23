package com.gocredit.service;

import com.gocredit.exceptions.UserNotFoundException;
import com.gocredit.model.Role;
import com.gocredit.model.User;
import com.gocredit.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    IUserRepository userRepository;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *Adds new user in the database
     * @param user
     * @return returns 
     * @throws UserNotFoundException
     */
    @Override
    public User signup(User user) throws UserNotFoundException {
        return userRepository.save(user);
    }

    @Override
    public User loginWithEmail(String email, String password) throws UserNotFoundException {
        return null;
    }

    @Override
    public User loginWithContactNumber(Long contactNumber, String password) throws UserNotFoundException {
        return null;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int deleteId) throws UserNotFoundException {

        userRepository.deleteById(deleteId);
    }

    @Override
    public User getById(int userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user found with the id of " + userId));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getByRole(String role) throws UserNotFoundException {
        List<User> users = userRepository.findByRole(Role.valueOf(role.toUpperCase()));
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with the role of " + role);
        }
        return users;
    }

    @Override
    public List<User> getByName(String name) throws UserNotFoundException {
        List<User> users = userRepository.findByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with the name of " + name);
        }
        return users;
    }

    @Override
    public List<User> getByEmail(String email) throws UserNotFoundException {
        List<User> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with the email " + email);
        }
        return users;
    }

    @Override
    public List<User> getByContactNumber(long contactNumber) throws UserNotFoundException {
        List<User> users = userRepository.findByContactNumber(contactNumber);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with the contact number " + contactNumber);
        }
        return users;
    }
}
