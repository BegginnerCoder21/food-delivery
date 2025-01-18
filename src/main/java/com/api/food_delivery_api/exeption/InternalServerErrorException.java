package com.api.food_delivery_api.exeption;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String message)
    {
        super(message);
    }
}
