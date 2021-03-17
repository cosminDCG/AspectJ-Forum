package com.company.exception;

public enum ErrorMessages {
    USER_NOT_FOUND("The user could not be found!"),
    EMAIL_ALREADY_EXISTS("There is already an account created for the email: "),
    WRONG_PASSWORD("Wrong password for the account: "),
    POSTS_NOT_FOUND("No posts found!");

    private final String message;

    ErrorMessages(String errorMessage) {
        this.message = errorMessage;
    }

    public String getMessage() {
        return message;
    }
}
