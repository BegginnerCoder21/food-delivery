package com.api.food_delivery_api.exeption;

public class UserNotFoundErrorException extends RuntimeException{

    public UserNotFoundErrorException(String message)
    {
        super(message);
    }
}
