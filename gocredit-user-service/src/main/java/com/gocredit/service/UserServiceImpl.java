package com.gocredit.service;

import com.gocredit.exceptions.*;
import com.gocredit.model.Role;
import com.gocredit.model.User;
import com.gocredit.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    IUserRepository userRepository;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Adds new user in the database
     *
     * @return returns the newly created user object from the database
     * @throws UserAlreadyExitsException if the provided user already exists in the database
     * @param`user User is a user object in the database
     */
    @Override
    public User signup(User user) throws UserAlreadyExitsException {
        String email = user.getEmail();
        Long contactNumber = user.getContactNumber();

        User user1 = null;
        user1 = userRepository.findByEmail(email);

        if (user1 != null) {
            throw new UserAlreadyExitsException("User already exists with the email id " + user.getEmail());
        }

        User user2 = null;
        user2 = userRepository.findByContactNumber(contactNumber);

        if (user2 != null) {
            throw new UserAlreadyExitsException("User already exists with the contact number " + user.getContactNumber());
        }

        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User newUser = userRepository.save(user);

        return newUser;
    }

    /**
     * Finds user based on email and password in the database
     *
     * @param email    email of the user
     * @param password password of the user
     * @return Returns the found user in the database
     * @throws UserNotFoundException When user is not found in the database
     */
    @Override
    public User loginWithEmail(String email, String password) throws UserNotFoundException {
        User user = null;
        user = userRepository.findByEmail(email);

        if (user == null) {
            throw new InvalidUserCredentials("Invalid User Credentials");
        }

        String hashedPassword = user.getPassword();
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (hashedPassword != null && bCryptPasswordEncoder.matches(password, hashedPassword)) {
            return user;
        }
        throw new InvalidUserCredentials("Invalid User Credentials");
    }


    /**
     * Finds User based on contact number and password in the database
     *
     * @param contactNumber contact number of the user
     * @param password      password of the user
     * @return Returns the found user in the database
     * @throws UserNotFoundException When user is not found in the database
     */
    @Override
    public User loginWithContactNumber(Long contactNumber, String password) throws UserNotFoundException {
        User user = null;
        user = userRepository.findByContactNumber(contactNumber);
        if (user == null) {
            throw new InvalidUserCredentials("Invalid User Credentials");
        }

        String hashedPassword = user.getPassword();
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (hashedPassword != null && bCryptPasswordEncoder.matches(password, hashedPassword)) {
            return user;
        }
        throw new InvalidUserCredentials("Invalid User Credentials");
    }

    /**
     * Updates a user in the database
     *
     * @param user User object with id
     * @return Returns the updated User from the database
     * @throws UserNotFoundException When user is not found in the database
     */
    @Override
    public User updateUser(User user) throws UserNotFoundException {

        String email = user.getEmail();
        Long contactNumber = user.getContactNumber();

        User user1 = null;
        user1 = userRepository.findByEmail(email);

        if (user1 == null) {
            throw new UserNotFoundException("No user found with the email of " + user.getEmail());
        }

        User user2 = null;
        user2 = userRepository.findByContactNumber(contactNumber);

        if (user2 == null) {
            throw new UserAlreadyExitsException("No user found with the contact number of " + user.getContactNumber());
        }

        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User updatedUser = userRepository.save(user);
        return userRepository.save(updatedUser);
    }

    /**
     * Delete a single user id in the database
     *
     * @param userId User id to delete the user in the database
     * @throws UserNotFoundException If the user could not be found in the database
     */
    @Override
    public void deleteUser(int userId) throws UserNotFoundException {

        userRepository.deleteById(userId);
    }


    /**
     * Finds a single user id  from the database based on the user id provided
     *
     * @param userId User id to find the user in the database
     * @return Returns the User found in the database
     * @throws UserNotFoundException If the user could not be found in the database
     */
    @Override
    public User getById(int userId) throws UserNotFoundException {

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user found with the id of " + userId));
    }

    /**
     * Returns all user in the database
     *
     * @return Returns all user in the database
     */
    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    /**
     * Finds users based on role of the user provided
     *
     * @param role Role of the user
     * @return Returns a list of user found in the database
     * @throws UserNotFoundException If no user is found in the database
     */
    @Override
    public List<User> getByRole(String role) throws UserNotFoundException {

        List<User> users = userRepository.findByRole(Role.valueOf(role.toUpperCase()));
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with the role of " + role);
        }
        return users;
    }

    /**
     * Finds users based on name provided
     *
     * @param name Name of the user in the database
     * @return Returns a list of users  found in the database
     * @throws UserNotFoundException If no user is found in the database
     */
    @Override
    public List<User> getByName(String name) throws UserNotFoundException {

        List<User> users = userRepository.findByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with the name of " + name);
        }
        return users;
    }

    /**
     * Finds users based on email provided
     *
     * @param email Email  of the user in the database
     * @return Returns a list of user found in the database
     * @throws UserNotFoundException If no user is found in the database
     */
    @Override
    public User getByEmail(String email) throws UserNotFoundException {
        User user = null;
        user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("No user found with the email of " + email);
        }
        return user;
    }

    /**
     * Finds User based on contact number provided
     *
     * @param contactNumber Contact number of the user in the database
     * @return Returns a list of user found in the database
     * @throws UserNotFoundException If no user is found in the database
     */
    @Override
    public User getByContactNumber(long contactNumber) throws UserNotFoundException {

        User user = null;
        user = userRepository.findByContactNumber(contactNumber);

        if (user == null) {
            throw new UserNotFoundException("No User found with the password of " + contactNumber);
        }

        return user;
    }

    /**
     * Finds User based on card number provided
     *
     * @param cardNumber Credit Card Number of the user in the database
     * @return Returns the user found in the database
     * @throws CreditCardNotFoundException If no user credit card number is found in the database
     */
    @Override
    public User getByCardNumber(String cardNumber) throws CreditCardNotFoundException {

        User user = userRepository.findByCardNumber(cardNumber).stream().findFirst()
                .orElseThrow(() -> new CreditCardNotFoundException("No User found with the cardNumber of " + cardNumber));
        return user;
    }

    /**
     * Finds User based on bill id provided
     *
     * @param billId Finds User based on bill id provided
     * @return Returns the bill found in the database
     * @throws BillNotFoundException If no bill is found with the provided id in the database
     */
    @Override
    public User getByBillId(int billId) throws BillNotFoundException {

        User user = userRepository.findByBillId(billId).stream().findFirst()
                .orElseThrow(() -> new BillNotFoundException("No User found with the billId of " + billId));
        return user;
    }

    /**
     * Finds User based on Is bill paid provided
     *
     * @param isPaid Is bill paid in the database
     * @return Returns a list of users found in the database
     * @throws UserNotFoundException If no user is found
     */
    @Override
    public List<User> getByIsBillPaid(boolean isPaid) throws UserNotFoundException {

        List<User> users = userRepository.findByIsBillPaid(isPaid);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No user found with the isPaid of " + isPaid);
        }
        return users;
    }
}
