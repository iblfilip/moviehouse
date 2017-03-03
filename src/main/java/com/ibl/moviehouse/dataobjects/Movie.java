package com.ibl.moviehouse.dataobjects;

import com.ibl.moviehouse.enums.MovieColumnEnum;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class Movie {
    Integer movieId;
    Integer userId;
    String title;
    String genre;
    Date seenDate;
    boolean seen;
    Integer ratTotal;
    Integer ratDirector;
    Integer ratActors;
    Integer ratStory;
    Integer ratVisual;



    public void setItem(String columnName, Object item){
        if(columnName.equals(MovieColumnEnum.movie_id.name()))
            setMovieId((Integer) item);
        else if(columnName.equals(MovieColumnEnum.user_id.name()))
            setUserId((Integer) item);
        else if(columnName.equals(MovieColumnEnum.title.name()))
            setTitle(item.toString());
        else if(columnName.equals(MovieColumnEnum.genre.name()))
            setGenre(item.toString());
        else if(columnName.equals(MovieColumnEnum.seen_date.name()))
            setSeenDate((Date)item);
        else if(columnName.equals(MovieColumnEnum.seen.name())) {
            if (item.equals(1))
                setSeen(true);
            else if (item.equals(0))
                setSeen(false);
        }
        else if(columnName.equals(MovieColumnEnum.rat_total.name()))
            setRatTotal((Integer)item);
        else if(columnName.equals(MovieColumnEnum.rat_director.name()))
            setRatDirector((Integer)item);
        else if(columnName.equals(MovieColumnEnum.rat_actors.name()))
            setRatActors((Integer)item);
        else if(columnName.equals(MovieColumnEnum.rat_story.name()))
            setRatStory((Integer)item);
        else if(columnName.equals(MovieColumnEnum.rat_visual.name()))
            setRatVisual((Integer)item);
    }


    public int getMovieId() {
        return movieId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public Date getSeenDate() {
        return seenDate;
    }

    public boolean isSeen() {
        return seen;
    }

    public Integer getRatTotal() {
        return ratTotal;
    }

    public Integer getRatDirector() {
        return ratDirector;
    }

    public Integer getRatActors() {
        return ratActors;
    }

    public Integer getRatStory() {
        return ratStory;
    }

    public Integer getRatVisual() {
        return ratVisual;
    }


    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSeenDate(Date seenDate) {
        this.seenDate = seenDate;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public void setRatTotal(Integer ratTotal) {
        this.ratTotal = ratTotal;
    }

    public void setRatDirector(Integer ratDirector) {
        this.ratDirector = ratDirector;
    }

    public void setRatActors(Integer ratActors) {
        this.ratActors = ratActors;
    }

    public void setRatStory(Integer ratStory) {
        this.ratStory = ratStory;
    }

    public void setRatVisual(Integer ratVisual) {
        this.ratVisual = ratVisual;
    }
}
