package com.ibl.moviehouse.tools;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class DateTimeTool {

    public Date parseToSqlDate(String date) {
        Date sqlDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(date);
            sqlDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    public java.sql.Timestamp getSqlCurrentTimestamp() {
        java.util.Date currentDate = new java.util.Date();
        Date currDate = new Date(currentDate.getTime());
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(currDate.getTime());
        return currentTimestamp;
    }

    public Date getSqlCurrentDate() {
        java.util.Date currentDate = new java.util.Date();
        Date currDate = new Date(currentDate.getTime());
        return currDate;
    }
}
