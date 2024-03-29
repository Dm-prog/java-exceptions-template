package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.User;
import com.epam.izh.rd.online.exception.NotCorrectPasswordException;
import com.epam.izh.rd.online.exception.SimplePasswordException;
import com.epam.izh.rd.online.exception.UserAlreadyRegisteredException;
import com.epam.izh.rd.online.exception.UserNotFoundException;

public interface IAuthenticationService {
    User login(User user) throws UserNotFoundException, NotCorrectPasswordException, SimplePasswordException, UserAlreadyRegisteredException;

    void logout();
}
