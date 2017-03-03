package com.ibl.moviehouse.database;

import com.ibl.moviehouse.dataobjects.Movie;
import com.ibl.moviehouse.enums.MovieColumnEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieMapper implements RowMapper<Movie> {
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException{
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt(MovieColumnEnum.movie_id.name()));
        movie.setTitle(rs.getString(MovieColumnEnum.title.name()));
        movie.setGenre(rs.getString(MovieColumnEnum.genre.name()));
        movie.setSeen(rs.getBoolean(MovieColumnEnum.seen.name()));
        movie.setSeenDate(rs.getDate(MovieColumnEnum.seen_date.name()));
        movie.setRatTotal((Integer)rs.getObject(MovieColumnEnum.rat_total.name()));
        movie.setRatDirector((Integer)rs.getObject(MovieColumnEnum.rat_director.name()));
        movie.setRatVisual((Integer)rs.getObject(MovieColumnEnum.rat_visual.name()));
        movie.setRatStory((Integer) rs.getObject(MovieColumnEnum.rat_story.name()));
        movie.setRatActors((Integer) rs.getObject(MovieColumnEnum.rat_actors.name()));
        movie.setUserId((Integer) rs.getObject(MovieColumnEnum.user_id.name()));
        return movie;
    }
}
