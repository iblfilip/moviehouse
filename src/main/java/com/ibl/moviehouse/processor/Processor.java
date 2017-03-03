package com.ibl.moviehouse.processor;

import com.google.identitytoolkit.GitkitUser;
import com.ibl.moviehouse.database.MovieJDBCTemplate;
import com.ibl.moviehouse.dataobjects.*;
import com.ibl.moviehouse.enums.*;
import com.ibl.moviehouse.tools.DateTimeTool;
import com.ibl.moviehouse.tools.GitKitTools;
import com.ibl.moviehouse.tools.RatingCountTool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Processor {
    public Logger logger = Logger.getLogger("ProcessorLogger");

    ApplicationContext context =
            new ClassPathXmlApplicationContext("Beans.xml");

    MovieJDBCTemplate movieJDBCTemplate = (MovieJDBCTemplate)context.getBean("movieJDBCTemplate");

    /*public List selectMovie(Object value, MovieColumnEnum columnName) {
        List movieList = null;
        try {
            movieList = moviesHandler.selectMovie(value, columnName.name());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exeption during execution of select statement ", e);
        }
        return movieList;
    }*/

    public Movie selectMovieAccId(int movieId) {
        Movie movie = null;
        movie = movieJDBCTemplate.selectMovie(movieId);
        return movie;
    }

    public List selectAllMovies(int userId) {
        List<Movie> values = null;
            values = movieJDBCTemplate.selectAllMovies(userId);
        return values;
    }

    public void updateMovie(Object changedValue, MovieColumnEnum changedColumn, int searchedValue) {
        movieJDBCTemplate.updateMovie(changedValue, changedColumn, searchedValue);
    }

    public void deleteMovie(int movieId) {
            movieJDBCTemplate.deleteMovie(movieId);
    }

    public void insertMovie(Movie movie) {
        RatingCountTool ratingCountTool = new RatingCountTool();
        movie = ratingCountTool.updateTotalRating(movie);
        movieJDBCTemplate.insertMovie(movie);

    }

    public void updateMovieDynamically(Movie movie) {

        movieJDBCTemplate.updateMovieDynamically(movie);
    }

    /**
     * Make a record in database with object user a return what is userId of new record in db
     * @param user
     * @return userId
     */
    public int insertUser(User user) {
        movieJDBCTemplate.insertUser(user);
        user = movieJDBCTemplate.selectUser(user.getUserId());
        return user.getUserId();
    }



    public int getUserId(String email){
        User user = movieJDBCTemplate.selectUser(email);
        return user.getUserId();
    }

    public Integer getUserId(HttpServletRequest request) {
        User user = null;
        GitKitTools gitKit = new GitKitTools();
        GitkitUser gitKitUser = gitKit.getGitKitUser(request);

        user = movieJDBCTemplate.selectUser(gitKitUser.getEmail());
        logger.log(Level.INFO, "user mail ", gitKitUser.getEmail());


        if (user.getUserId().equals(null)== false)
            return (int) (long) user.getUserId();
        else return null;

    }

    public GitkitUser getGitKitUser(HttpServletRequest request) {
        GitKitTools gitKit = new GitKitTools();
        GitkitUser gitKitUser = gitKit.getGitKitUser(request);
        return gitKitUser;
    }

    /**
     * Checks if user from gitKitUser has record in database, if not, new record is made
     * @param gitkitUser
     * @return userId from database
     */
    public int doUserLogic(GitkitUser gitkitUser) {
        DateTimeTool tool = new DateTimeTool();
        User user = null;
        String email = null;
        int userId;
        email = gitkitUser.getEmail();
        user = movieJDBCTemplate.selectUser(email);


        if (user.getUserId().equals(null) == false) {
            userId = user.getUserId();
            movieJDBCTemplate.updateUser(tool.getSqlCurrentTimestamp(), UsersColumnEnum.user_last_sign_in, user.getUserId());
            logger.log(Level.INFO, "Returning user, found in db");
            return userId;

        } else {
            logger.log(Level.INFO, "User didnt found in db, creating new record");
            user.setEmail(gitkitUser.getEmail());
            user.setName(gitkitUser.getName());
            user.setHashCode(gitkitUser.getHash());
            user.setCurrentProvider(gitkitUser.getCurrentProvider());
            user.setUserPhotoUrl(gitkitUser.getPhotoUrl());
            user.setRegistrationDate(tool.getSqlCurrentDate());
            user.setLastSignIn(tool.getSqlCurrentTimestamp());
            userId = insertUser(user);
            return userId;
        }

    }
}

