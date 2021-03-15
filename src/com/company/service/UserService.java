package com.company.service;

import com.company.dao.UserDAO;
import com.company.dto.UserDTO;
import com.company.exception.EmailAlreadyExistsException;
import com.company.exception.ErrorMessages;
import com.company.exception.UserNotFoundException;
import com.company.exception.WrongPasswordException;

public class UserService {

    private static final UserService userServiceInstance = new UserService();

    private UserService(){}

    public static UserService getInstance() {
        return userServiceInstance;
    }

    public UserDTO getUserByEmail(String email) {
        return UserDAO.getInstance().getUserByEmail(email);
    }

    public void addUser(UserDTO userDTO) {
        UserDAO.getInstance().addUser(userDTO);
    }

    public String registerUser(UserDTO userDTO) {
        UserDTO user = getUserByEmail(userDTO.getEmail());
        if(user != null) {
            throw new EmailAlreadyExistsException(ErrorMessages.EMAIL_ALREADY_EXISTS.getMessage() + userDTO.getEmail());
        }
        UserDAO.getInstance().addUser(userDTO);
        return "Success";
    }

    public UserDTO logIn(String email, String password) {
        UserDTO user = getUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(ErrorMessages.USER_NOT_FOUND.getMessage());
        } else if(user.getPassword().equals(password)) {
            return user;
        } else {
            throw new WrongPasswordException(ErrorMessages.WRONG_PASSWORD.getMessage() + email);
        }
    }

    public int deleteUser(String email) {
        UserDAO.getInstance().deleteUser(email);
        return 1;
    }
}
