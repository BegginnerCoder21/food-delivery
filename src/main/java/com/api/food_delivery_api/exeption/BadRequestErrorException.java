package com.api.food_delivery_api.exeption;

public class BadRequestErrorException extends RuntimeException{

    public BadRequestErrorException(String message)
    {
        super(message);
    }
}
