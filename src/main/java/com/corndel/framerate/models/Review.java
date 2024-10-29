package com.corndel.framerate.models;

public class Review {
  private int id;
  public int movieId;
  public long createdAt;
  public int rating;
  public String content;
  public String movieTitle;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public Review() {
  }

  public Review(int id, int movieId, long createdAt, int rating, String content) {
    this.id = id;
    this.movieId = movieId;
    this.createdAt = createdAt;
    this.rating = rating;
    this.content = content;
  }

  public Review(int id, int movieId, long createdAt, int rating, String content, String movieTitle) {
    this.id = id;
    this.movieId = movieId;
    this.createdAt = createdAt;
    this.rating = rating;
    this.content = content;
    this.movieTitle = movieTitle;
  }


}
