package com.ibl.moviehouse.dataobjects;

import java.util.List;

/**
 * Created by Filip on 31.01.2017.
 */
public class MovieForm {

    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies=movies;
    }
}
