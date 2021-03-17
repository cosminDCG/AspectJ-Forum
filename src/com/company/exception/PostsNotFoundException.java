package com.company.exception;

public class PostsNotFoundException extends RuntimeException {
    public PostsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
