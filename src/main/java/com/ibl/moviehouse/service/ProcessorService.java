package com.ibl.moviehouse.service;

import com.google.identitytoolkit.GitkitUser;
import com.ibl.moviehouse.dataobjects.Movie;
import com.ibl.moviehouse.dataobjects.User;
import com.ibl.moviehouse.enums.MovieColumnEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProcessorService {

    Movie selectMovieAccId(int movieId);

    List selectAllMovies(int userId);

    void updateMovie(Object changedValue, MovieColumnEnum changedColumn, int searchedValue);

    void deleteMovie(int movieId);

    void insertMovie(Movie movie);

    void updateMovieDynamically(Movie movie);

    int insertUser(User user);

    int getUserId(String email);

    Integer getUserId(HttpServletRequest request);

    GitkitUser getGitKitUser(HttpServletRequest request);

    int doUserLogic(GitkitUser gitkitUser);



}
