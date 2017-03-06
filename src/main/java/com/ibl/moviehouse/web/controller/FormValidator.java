package com.ibl.moviehouse.web.controller;

import com.ibl.moviehouse.dataobjects.Movie;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * validating form
 * title, genre are required fields
 * seenDate must be in yyyy-MM-dd format
 */
@Component
public class FormValidator implements Validator {

    private static final Logger logger = Logger.getLogger(FormValidator.class.getName());

    public boolean supports(Class<?> clazz) {
        return Movie.class.isAssignableFrom(clazz);
    }


    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title", "title error message");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "error.genre", "genre error message");
        Movie movie = (Movie) target;
        Date date = null;
        boolean marker = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (movie.getSeenDate().equals(""))
                marker = true;
                // in case that field is not filled, the rejection of value won´t happen
            else {
                if (movie.getSeenDate().indexOf("-") != 4 || movie.getSeenDate().lastIndexOf("-") != 7) {
                    marker = true;
                    errors.rejectValue("seenDate", "error.seenDate");
                    //date must have two '-' chars at right places
                } else if (marker == false) {
                    date = sdf.parse(movie.getSeenDate()); // checks if
                    if (!movie.getSeenDate().equals(sdf.format(date))) {
                        errors.rejectValue("seenDate", "error.seenDate");
                        // compare date with SimpleDateFormat
                    }
                }
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
            errors.rejectValue("seenDate", "error.seenDate");
        }
    }
}
