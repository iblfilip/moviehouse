package com.ibl.moviehouse.web.controller;

import com.ibl.moviehouse.dataobjects.Movie;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Filip on 20.02.2017.
 */

@Component
public class FormValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Movie.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Movie movie = (Movie) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Title is required");

        if(movie.getTitle()==null) {
            errors.reject("title", "value required");
        }

        //DateTimeFormat.ISO.DATE_TIME.equals()
        //if(movie.getSeenDate().)
    }

}
