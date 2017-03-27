package com.ibl.moviehouse.dataobjects;

public class Movie {
    Integer movieId;
    Integer userId;
    String title;
    String genre;
    String seenDate;
    boolean seen;
    Integer ratTotal;
    Integer ratDirector;
    Integer ratActors;
    Integer ratStory;
    Integer ratVisual;

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

    public String getSeenDate() {
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

    public void setSeenDate(String seenDate) {
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

    public String toString() {
        return "movie: movieId= " + movieId + ", title=" + title + ", genre=" + genre + ", seen=" + seen + ", seenDate=" + seenDate + ", total rating=" + ratTotal + ", rating director=" + ratDirector + ", rating actors=" + ratActors + ", rating visual=" + ratVisual + ", rating story=" + ratStory;
    }
}
