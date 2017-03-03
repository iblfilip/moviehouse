package com.ibl.moviehouse.database;

import com.ibl.moviehouse.dataobjects.User;
import com.ibl.moviehouse.enums.UsersColumnEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt(UsersColumnEnum.user_id.name()));
        user.setEmail(rs.getString(UsersColumnEnum.user_email.name()));
        user.setName(rs.getString(UsersColumnEnum.user_name.name()));
        user.setCurrentProvider(rs.getString(UsersColumnEnum.current_provider.name()));
        user.setUserPhotoUrl(rs.getString(UsersColumnEnum.user_photo_url.name()));
        user.setRegistrationDate(rs.getDate(UsersColumnEnum.user_registration_date.name()));
        user.setLastSignIn(rs.getTimestamp(UsersColumnEnum.user_last_sign_in.name()));
        return user;
    }
}
