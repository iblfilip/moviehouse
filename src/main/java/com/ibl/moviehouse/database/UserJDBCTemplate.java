package com.ibl.moviehouse.database;

import com.ibl.moviehouse.dataobjects.User;
import com.ibl.moviehouse.enums.TableNameEnum;
import com.ibl.moviehouse.enums.UsersColumnEnum;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserJDBCTemplate implements UserDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = Logger.getLogger(JdbcTemplate.class.getName());

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        logger.log(Level.INFO, "Database successfully opened");
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO " + TableNameEnum.users.name() + " (" + UsersColumnEnum.user_email.name() + "," + UsersColumnEnum.user_name.name() + "," + UsersColumnEnum.current_provider.name() + "," + UsersColumnEnum.user_photo_url.name() + "," + UsersColumnEnum.user_registration_date.name() + "," + UsersColumnEnum.user_last_sign_in.name() + "," + UsersColumnEnum.hash_code.name() + ") VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getName(), user.getCurrentProvider(), user.getUserPhotoUrl(), user.getRegistrationDate(), user.getLastSignIn(), user.getHashCode());
        logger.log(Level.INFO, "Inserted user " + user.toString());
        return;
    }
    public User selectUser(Integer userId) {
        String sql = "SELECT * FROM " + TableNameEnum.users.name() + " WHERE " + UsersColumnEnum.user_id.name() + "=?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserMapper());
        logger.log(Level.INFO, "selected " + user.toString());
        return user;
    }

    public User selectUser(String email) {
        User user = null;
        String sql = "SELECT * FROM " + TableNameEnum.users.name() + " WHERE " + UsersColumnEnum.user_email.name() + "=?";
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        logger.log(Level.INFO, "selected " + user.toString());
        return user;
    }

    public void updateUser(Object updated, UsersColumnEnum column, Integer userId) {
        String sql = "UPDATE " + TableNameEnum.users + " SET " + column + "=? WHERE " + UsersColumnEnum.user_id.name() + " =?";
        jdbcTemplate.update(sql, updated, userId);
        logger.log(Level.INFO, "updated user with id = " + userId + ", updated column " + column + ", set value " + updated.toString());
        return;
    }

}
