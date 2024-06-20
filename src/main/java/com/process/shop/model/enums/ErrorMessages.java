package com.process.shop.model.enums;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    ARTICLE_NOT_FOUND("Article not found!"),
    ARTICLE_CODE_EXISTS("The code of the article is already registered"),
    USER_NOT_FOUND("User not found!"),
    USER_EMAIL_EXISTS("The email is already registered"),
    CREDENTIAL_INVALID("The credentials is invalid");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
