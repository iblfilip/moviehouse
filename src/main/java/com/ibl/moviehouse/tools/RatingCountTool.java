package com.ibl.moviehouse.tools;

import com.ibl.moviehouse.dataobjects.Movie;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RatingCountTool {

    private static final Logger logger = Logger.getLogger(RatingCountTool.class.getName());

    public Movie updateTotalRating(Movie movie) {
        int count = 4;
        int total = 0;
        Integer [] arr = {movie.getRatDirector(), movie.getRatActors(), movie.getRatStory(), movie.getRatVisual()};
        for(int i=0; i<4; i++) {
            if (arr[i]==null)
                count -= 1;
            if (arr[i]!=null)
                total += arr[i];
        }
        if(count != 0) {
            total = total/count;
            logger.log(Level.INFO, total + " , " + count + "counter is:<<<");
            movie.setRatTotal(total);
        }
        return movie;
    }
}
