package com.ibl.moviehouse.database;

import com.ibl.moviehouse.dataobjects.User;
import com.ibl.moviehouse.enums.UsersColumnEnum;

import javax.sql.DataSource;

public interface UserDAO {
    void setDataSource(DataSource dataSource);

    void insertUser(User user);

    User selectUser(Integer userId);

    User selectUser(String email);

    void updateUser(Object updated, UsersColumnEnum column, Integer userId);
}
