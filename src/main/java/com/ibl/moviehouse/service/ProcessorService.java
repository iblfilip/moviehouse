package com.ibl.moviehouse.service;

import com.google.identitytoolkit.GitkitUser;
import com.ibl.moviehouse.dataobjects.Movie;
import com.ibl.moviehouse.dataobjects.User;
import com.ibl.moviehouse.enums.MovieColumnEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Filip on 07.03.2017.
 */
public interface ProcessorService {

    public Movie selectMovieAccId(int movieId);

    public List selectAllMovies(int userId);

    public void updateMovie(Object changedValue, MovieColumnEnum changedColumn, int searchedValue);

    public void deleteMovie(int movieId);

    public void insertMovie(Movie movie);

    public void updateMovieDynamically(Movie movie);

    public int insertUser(User user);

    public int getUserId(String email);

    public Integer getUserId(HttpServletRequest request);

    public GitkitUser getGitKitUser(HttpServletRequest request);

    public int doUserLogic(GitkitUser gitkitUser);



}
