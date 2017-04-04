package com.ibl.moviehouse.service;

import com.google.identitytoolkit.GitkitUser;
import com.ibl.moviehouse.database.MovieJDBCTemplate;
import com.ibl.moviehouse.database.UserJDBCTemplate;
import com.ibl.moviehouse.dataobjects.*;
import com.ibl.moviehouse.enums.*;
import com.ibl.moviehouse.tools.DateTimeTool;
import com.ibl.moviehouse.tools.GitKitTools;
import com.ibl.moviehouse.tools.RatingCountTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("processorService")
public class ProcessorServiceImpl implements ProcessorService {
    public Logger logger = Logger.getLogger(ProcessorServiceImpl.class.getName());

    ApplicationContext context =
            new ClassPathXmlApplicationContext("Beans.xml");
    MovieJDBCTemplate movieJDBCTemplate = (MovieJDBCTemplate) context.getBean("movieJDBCTemplate");
    UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");

    @Autowired
    GitKitTools gitKit;

    @Autowired
    RatingCountTool ratingCountTool;

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
        movie = ratingCountTool.updateTotalRating(movie);
        movieJDBCTemplate.insertMovie(movie);

    }

    public void updateMovieDynamically(Movie movie) {
        movieJDBCTemplate.updateMovieDynamically(movie);
    }

    /**
     * Make a record in database with object user a return what is userId of new record in db
     *
     * @param user
     * @return userId
     */
    public int insertUser(User user) {
        userJDBCTemplate.insertUser(user);
        user = userJDBCTemplate.selectUser(user.getEmail());
        return user.getUserId();
    }


    public int getUserId(String email) {
        User user = userJDBCTemplate.selectUser(email);
        return user.getUserId();
    }

    public Integer getUserId(HttpServletRequest request) {
        User user = null;
        GitkitUser gitKitUser = gitKit.getGitKitUser(request);
        user = userJDBCTemplate.selectUser(gitKitUser.getEmail());

        if (user.getUserId().equals(null) == false)
            return (int) (long) user.getUserId();
        else return null;

    }

    public GitkitUser getGitKitUser(HttpServletRequest request) {
        GitkitUser gitKitUser = gitKit.getGitKitUser(request);
        return gitKitUser;
    }

    /**
     * Checks if user from gitKitUser has record in database, if not, new record is made in database
     *
     * @param gitkitUser
     * @return userId from database
     */
    public int doUserLogic(GitkitUser gitkitUser) {
        DateTimeTool tool = new DateTimeTool();
        User user = null;
        String email = null;
        email = gitkitUser.getEmail();
        user = userJDBCTemplate.selectUser(email);

        if (user != null) {
            userJDBCTemplate.updateUser(tool.getSqlCurrentTimestamp(), UsersColumnEnum.user_last_sign_in, user.getUserId());
            logger.log(Level.INFO, "Returning user, found in db");
            return user.getUserId();

        } else {
            logger.log(Level.INFO, "User didnt found in db, creating new record");
            user = new User();
            user.setEmail(gitkitUser.getEmail());
            user.setName(gitkitUser.getName());
            user.setHashCode(gitkitUser.getHash());
            user.setCurrentProvider(gitkitUser.getCurrentProvider());
            user.setUserPhotoUrl(gitkitUser.getPhotoUrl());
            user.setRegistrationDate(tool.getSqlCurrentDate());
            user.setLastSignIn(tool.getSqlCurrentTimestamp());
            user.setUserId(insertUser(user));
            return user.getUserId();
        }
    }
}

