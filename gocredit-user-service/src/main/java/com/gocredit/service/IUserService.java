package com.gocredit.service;

import com.gocredit.exceptions.UserNotFoundException;
import com.gocredit.model.User;

public interface IUserService {

    public User addUser(User user);

    public User getById(int userId) throws UserNotFoundException;


}
