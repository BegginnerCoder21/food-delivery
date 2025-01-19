package com.api.food_delivery_api.utils;

import com.api.food_delivery_api.exeption.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class UserUtils {

    public static void usernameNotEmpty(String username)
    {
        if(StringUtils.hasText(username))
        {
            return;
        }

        throw new InternalServerErrorException("Le nom d'utilisateur est vide !");
    }

    public static void phoneNumberNotEmpty(String phoneNumber)
    {
        if(StringUtils.hasText(phoneNumber))
        {
            return;
        }

        throw new InternalServerErrorException("Le numero fournit est vide !");
    }

    public static Date convertStringToDate(String dateOfBirth)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {

            return dateFormat.parse(dateOfBirth);

        } catch (Exception e) {
            log.info("Erreur de conversion de date pour l'anniversaire");
            throw new InternalServerErrorException("Erreur de conversion de date pour l'anniversaire");
        }
    }
}
