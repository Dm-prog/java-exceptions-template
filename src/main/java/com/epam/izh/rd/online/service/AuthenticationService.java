package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.User;
import com.epam.izh.rd.online.exception.NotCorrectPasswordException;
import com.epam.izh.rd.online.exception.SimplePasswordException;
import com.epam.izh.rd.online.exception.UserAlreadyRegisteredException;
import com.epam.izh.rd.online.exception.UserNotFoundException;
import com.epam.izh.rd.online.repository.IUserRepository;

public class AuthenticationService implements IAuthenticationService {

    private IUserRepository userRepository;

    public AuthenticationService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Необходимо доработать данный метод следующим функционлом:
     * <p>
     * 1) Необходимо проверять наличие авторизующегося пользователя в списке зарегистрированных пользователей.
     * Если пользователь не найден, необходимо выбрасывать проверяемое исключение с названием UserNotFoundException
     * и текстом ошибки "Пользователь с таким логином не найден".
     * <p>
     * 2) Необходимо проверять, что пароль, который ввел пользователь совпадает с тем, что хранится в базе.
     * Если пароли не совпадают, необходимо выбрасывать исключение с названием NotCorrectPasswordException и
     * текстом "Пароль введен неверно!"
     *
     * @param user - пользователь проходящий авторизацию
     */
    @Override
    public User login(User user) throws UserNotFoundException, NotCorrectPasswordException {

        // Находим пользователя в базе
        User foundUser = userRepository.findByLogin(user.getLogin());

        //
        // Здесь необходимо реализовать перечисленные выше проверки
        //
        if (foundUser == null) {
            throw new UserNotFoundException("Пользователь с таким логином не найден");
        }

        if (!user.getPassword().equals(foundUser.getPassword())) {
            throw new NotCorrectPasswordException("Пароль введен неверно!");
        }
        userRepository.findByLogin(user.getLogin());

        // Устанавливаем найденного пользователя, который прошел все проверки, как вошедшего в систему.
        CurrentUserManager.setCurrentLoggedInUser(foundUser);
        return foundUser;
    }

    /**
     * Данный метод очищает данные о текущем (активном) пользователе.
     */
    @Override
    public void logout() {
        CurrentUserManager.setCurrentLoggedInUser(null);
    }
}