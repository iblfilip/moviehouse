package com.ibl.moviehouse.database;

import com.ibl.moviehouse.dataobjects.*;
import com.ibl.moviehouse.enums.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Filip on 27.01.2017.
 */
public class MovieJDBCTemplate implements MovieDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Logger logger = Logger.getLogger("MovieJDBCTemplate");

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        logger.log(Level.INFO, ">> Database opened");
    }

    public void insertMovie(Movie movie){
        String sql = "INSERT INTO " + TableNameEnum.movies.name() + " (" + MovieColumnEnum.user_id.name()+ "," + MovieColumnEnum.title.name() +","+ MovieColumnEnum.genre.name() +","+ MovieColumnEnum.seen_date.name() +","+ MovieColumnEnum.seen.name() +","+ MovieColumnEnum.rat_total.name() +","+ MovieColumnEnum.rat_director.name() +","+ MovieColumnEnum.rat_actors.name() +","+ MovieColumnEnum.rat_story.name() +","+ MovieColumnEnum.rat_visual.name() + ") VALUES (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, movie.getUserId(), movie.getTitle(), movie.getGenre(), movie.getSeenDate(), movie.isSeen(), movie.getRatTotal(), movie.getRatDirector(), movie.getRatActors(), movie.getRatStory(), movie.getRatVisual());
        logger.log(Level.INFO, "Inserted movie "+ movie.getTitle());
        return;
    }
    public Movie selectMovie(Integer movieId){
        String sql = "SELECT * FROM " + TableNameEnum.movies.name() + " WHERE " + MovieColumnEnum.movie_id + "=?";
        Movie movie = jdbcTemplate.queryForObject(sql, new Object[]{movieId}, new MovieMapper());
        logger.log(Level.INFO, "selected movie with id = "+ movie.getMovieId() + ", name = "+ movie.getTitle());
        return movie;
    }

    public List selectAllMovies(Integer userId){
        String sql = "SELECT * FROM "+ TableNameEnum.movies + " where " + MovieColumnEnum.user_id + " = ?;";
        List <Movie> list = jdbcTemplate.query(sql, new Object[]{userId}, new MovieMapper());
        logger.log(Level.INFO, "selected all movies with userId = " + userId);
        return list;
    }

    public void updateMovie(Object updated, MovieColumnEnum column, Integer movieId){
        String sql = "UPDATE " + TableNameEnum.movies + " SET " + column + "=? WHERE " + MovieColumnEnum.movie_id + " =?";
        jdbcTemplate.update(sql, updated, movieId);
        logger.log(Level.INFO, "updated movie with id = " + movieId + ", updated column " + column + ", set value " + updated.toString());
        return;
    }

    public void deleteMovie(Integer movieId) {
        String sql = "DELETE FROM " + TableNameEnum.movies + " WHERE " + MovieColumnEnum.movie_id + " = ?";
        jdbcTemplate.update(sql, movieId);
        logger.log(Level.INFO, "deleted movie with id = " + movieId);
        return;
    }

    public void insertUser(User user){
        String sql = "INSERT INTO " + TableNameEnum.users.name() + " (" + UsersColumnEnum.user_email.name() + "," + UsersColumnEnum.user_name.name() + "," + UsersColumnEnum.current_provider.name() + "," + UsersColumnEnum.user_photo_url.name() + "," + UsersColumnEnum.user_registration_date.name() + "," + UsersColumnEnum.user_last_sign_in.name() + "," + UsersColumnEnum.hash_code.name() + ") VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getName(), user.getCurrentProvider(), user.getUserPhotoUrl(), user.getRegistrationDate(), user.getLastSignIn(), user.getHashCode());
        logger.log(Level.INFO, "Inserted user "+ user.getEmail());
        return;
    }

    public User selectUser(Integer userId){
        String sql = "SELECT * FROM " + TableNameEnum.users.name() + " WHERE " + UsersColumnEnum.user_id.name() + "=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserMapper());
        logger.log(Level.INFO, "selected user with id = "+ user.getUserId() + ", email = "+ user.getEmail());
        return user;
    }

    public User selectUser(String email){
        String sql = "SELECT * FROM " + TableNameEnum.users.name() + " WHERE " + UsersColumnEnum.user_email.name() + "=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
        logger.log(Level.INFO, "selected user with id = "+ user.getUserId() + ", email = "+ user.getEmail());
        return user;
    }

    public void updateUser(Object updated, UsersColumnEnum column, Integer userId){
        String sql = "UPDATE " + TableNameEnum.users + " SET " + column + "=? WHERE " + UsersColumnEnum.user_id.name() + " =?";
        jdbcTemplate.update(sql, updated, userId);
        logger.log(Level.INFO, "updated user with id = " + userId + ", updated column " + column + ", set value " + updated.toString());
        return;
    }

    public void updateMovieDynamically(Movie movie) {
        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("UPDATE " + TableNameEnum.movies + " SET ");
        updateQuery.append("user_id = ?");
        if(movie.getTitle()!=null) updateQuery.append(", title = ?");
        if(movie.getGenre()!=null) updateQuery.append(", genre = ?");
        updateQuery.append(", seen = ?");
        if(movie.getSeenDate() !=null) updateQuery.append(", seen_date = ?");
        updateQuery.append(", rat_total = ?");
        updateQuery.append(", rat_director = ?");
        updateQuery.append(", rat_actors = ?");
        updateQuery.append(", rat_visual = ?");
        updateQuery.append(", rat_story = ?");
        updateQuery.append(" WHERE " + MovieColumnEnum.movie_id + " = ?");

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(movie.getUserId());
        if(movie.getTitle() != null) params.add(movie.getTitle());
        if(movie.getGenre() != null) params.add(movie.getGenre());
        params.add(movie.isSeen());
        if(movie.getSeenDate() != null) params.add(movie.getSeenDate());
        params.add(movie.getRatTotal());
        params.add(movie.getRatDirector());
        params.add(movie.getRatActors());
        params.add(movie.getRatVisual());
        params.add(movie.getRatStory());
        params.add(movie.getMovieId());
        int row = jdbcTemplate.update(updateQuery.toString(), params.toArray());


    }


}
