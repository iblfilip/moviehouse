package com.ibl.moviehouse.database;


import com.ibl.moviehouse.dataobjects.Movie;
import com.ibl.moviehouse.enums.MovieColumnEnum;
import com.ibl.moviehouse.dataobjects.User;
import com.ibl.moviehouse.enums.UsersColumnEnum;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Filip on 27.01.2017.
 */
public interface MovieDAO {

    public void setDataSource(DataSource dataSource);

    public void insertMovie(Movie movie);

    public Movie selectMovie(Integer movieId);

    public List<Movie> selectAllMovies(Integer userId);

    public void deleteMovie(Integer movieId);

    public void updateMovie(Object updated, MovieColumnEnum column, Integer movieId);

    public void insertUser(User user);

    public User selectUser(Integer userId);

    public User selectUser(String email);

    public void updateUser(Object updated, UsersColumnEnum column, Integer userId);

    public void updateMovieDynamically(Movie movie);

}
