package com.ibl.moviehouse.database;


import com.ibl.moviehouse.dataobjects.Movie;
import com.ibl.moviehouse.enums.MovieColumnEnum;
import com.ibl.moviehouse.dataobjects.User;
import com.ibl.moviehouse.enums.UsersColumnEnum;

import javax.sql.DataSource;
import java.util.List;

public interface MovieDAO {

    void setDataSource(DataSource dataSource);

    void insertMovie(Movie movie);

    Movie selectMovie(Integer movieId);

    List<Movie> selectAllMovies(Integer userId);

    void deleteMovie(Integer movieId);

    void updateMovie(Object updated, MovieColumnEnum column, Integer movieId);

    void insertUser(User user);

    User selectUser(Integer userId);

    User selectUser(String email);

    void updateUser(Object updated, UsersColumnEnum column, Integer userId);

    void updateMovieDynamically(Movie movie);

}
