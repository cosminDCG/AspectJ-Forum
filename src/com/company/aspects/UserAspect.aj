package com.company.aspects;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.company.dto.UserDTO;
import com.company.service.UserService;

public aspect UserAspect {
    Logger LOG = Logger.getLogger("com.company");

    pointcut callLogin(String email) :
                call(UserDTO UserService.logIn(String, String)) && args(email, ..);

    before(String email): callLogin(email) {
        LOG.log(Level.INFO, "Login method triggered for email " + email);
    }

    after(String email) returning(UserDTO userDTO): callLogin(email) {
        LOG.log(Level.INFO, "Login succeeded for email " + email +
                " with details id: " + userDTO.getUserId()  +
                "; name: " + userDTO.getFirstName() + " " +userDTO.getLastName());
    }

    after(String email) throwing(RuntimeException e): callLogin(email) {
        LOG.log(Level.SEVERE, e.getMessage());
    }

    pointcut callRegister(UserDTO userDTO) :
                call(String UserService.registerUser(UserDTO)) && args(userDTO);

    before(UserDTO userDTO): callRegister(userDTO) {
        LOG.log(Level.INFO, "Register triggered with the following details: " + userDTO.toString());
    }

    after(UserDTO userDTO) returning(String message): callRegister(userDTO) {
        LOG.log(Level.INFO, "Register finished with status " + message + " for email " + userDTO.getEmail());
    }

    after(UserDTO userDTO) throwing(RuntimeException e): callRegister(userDTO) {
        LOG.log(Level.SEVERE, e.getMessage());
    }
}
