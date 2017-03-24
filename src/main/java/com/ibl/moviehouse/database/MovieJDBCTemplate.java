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
    private static final Logger logger = Logger.getLogger(JdbcTemplate.class.getName());

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        logger.log(Level.INFO, ">> Database opened");
    }

    public void insertMovie(Movie movie){
        StringBuilder insertQuery = new StringBuilder();
        insertQuery.append("INSERT INTO " + TableNameEnum.movies + " (");
        insertQuery.append(MovieColumnEnum.user_id);
        insertQuery.append(", "+MovieColumnEnum.title);
        insertQuery.append(", " + MovieColumnEnum.genre);
        insertQuery.append(", "+ MovieColumnEnum.seen);
        insertQuery.append(", " + MovieColumnEnum.seen_date);
        insertQuery.append(", " + MovieColumnEnum.rat_total);
        insertQuery.append(", " + MovieColumnEnum.rat_director);
        insertQuery.append(", "+ MovieColumnEnum.rat_actors);
        insertQuery.append(", " + MovieColumnEnum.rat_visual);
        insertQuery.append(", " + MovieColumnEnum.rat_story + ")");
        insertQuery.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(movie.getUserId());
        params.add(movie.getTitle());
        params.add(movie.getGenre());
        params.add(movie.isSeen());
        if(!movie.getSeenDate().equals("")) params.add(movie.getSeenDate());
        else if(movie.getSeenDate().equals("")) params.add(null);
        params.add(movie.getRatTotal());
        params.add(movie.getRatDirector());
        params.add(movie.getRatActors());
        params.add(movie.getRatVisual());
        params.add(movie.getRatStory());
        int row = jdbcTemplate.update(insertQuery.toString(), params.toArray());

        logger.log(Level.INFO, "Inserted movie "+ movie.toString());
    }
    public Movie selectMovie(Integer movieId){
        String sql = "SELECT * FROM " + TableNameEnum.movies.name() + " WHERE " + MovieColumnEnum.movie_id + "=?";
        Movie movie = jdbcTemplate.queryForObject(sql, new Object[]{movieId}, new MovieMapper());
        logger.log(Level.INFO, "selected " + movie.toString());
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
        logger.log(Level.INFO, "Inserted user "+ user.toString());
        return;
    }

    public User selectUser(Integer userId){
        String sql = "SELECT * FROM " + TableNameEnum.users.name() + " WHERE " + UsersColumnEnum.user_id.name() + "=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserMapper());
        logger.log(Level.INFO, "selected " + user.toString());
        return user;
    }

    public User selectUser(String email){
        String sql = "SELECT * FROM " + TableNameEnum.users.name() + " WHERE " + UsersColumnEnum.user_email.name() + "=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
        logger.log(Level.INFO, "selected "+ user.toString());
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
        updateQuery.append(MovieColumnEnum.user_id + " = ?");
        if(movie.getTitle()!=null) updateQuery.append(", " + MovieColumnEnum.title + " = ?");
        if(movie.getGenre()!=null) updateQuery.append(", " + MovieColumnEnum.genre + " = ?");
        updateQuery.append(", " + MovieColumnEnum.seen + " = ?");
        updateQuery.append(", " + MovieColumnEnum.seen_date + " = ?");
        updateQuery.append(", " + MovieColumnEnum.rat_total + " = ?");
        updateQuery.append(", " + MovieColumnEnum.rat_director + " = ?");
        updateQuery.append(", " + MovieColumnEnum.rat_actors + " = ?");
        updateQuery.append(", " + MovieColumnEnum.rat_visual + " = ?");
        updateQuery.append(", " + MovieColumnEnum.rat_story + " = ?");
        updateQuery.append(" WHERE " + MovieColumnEnum.movie_id + " = ?");

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(movie.getUserId());
        if(movie.getTitle() != null) params.add(movie.getTitle());
        if(movie.getGenre() != null) params.add(movie.getGenre());
        params.add(movie.isSeen());
        if(!movie.getSeenDate().equals("")) params.add(movie.getSeenDate());
        else if(movie.getSeenDate().equals("")) params.add(null);
        params.add(movie.getRatTotal());
        params.add(movie.getRatDirector());
        params.add(movie.getRatActors());
        params.add(movie.getRatVisual());
        params.add(movie.getRatStory());
        params.add(movie.getMovieId());
        int row = jdbcTemplate.update(updateQuery.toString(), params.toArray());

        logger.log(Level.INFO, "updated " + movie.toString());
    }


}
