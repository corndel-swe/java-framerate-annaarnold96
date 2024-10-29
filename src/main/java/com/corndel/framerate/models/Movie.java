package com.corndel.framerate.models;

import java.util.List;

public class Movie {
  public enum Genre {
    Adventure,
    Action,
    Animation,
    Biography,
    Comedy,
    Crime,
    Drama,
    Family,
    Fantasy,
    History,
    Horror,
    Mystery,
    Romance,
    SciFi,
    Thriller,
    War
  }

  public Movie() {
  }

  public Movie(
      int id,
      String title,
      String releaseDate,
      String ageRating,
      List<Genre> genres,
      int runtime,
      String imageURL) {
    this.id = id;
    this.title = title;
    this.releaseDate = releaseDate;
    this.ageRating = ageRating;
    this.genres = genres;
    this.runtime = runtime;
    this.imageURL = imageURL;
  }

  private int id;
  public String title;
  public String releaseDate;
  public String ageRating;
  public List<Genre> genres;
  public int runtime;
  public String imageURL;

  @Override
  public String toString() {
    return "Movie{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", releaseDate='" + releaseDate + '\'' +
            ", ageRating='" + ageRating + '\'' +
            ", genres=" + genres +
            ", runtime=" + runtime +
            ", imageURL='" + imageURL + '\'' +
            '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getAgeRating() {
    return ageRating;
  }

  public void setAgeRating(String ageRating) {
    this.ageRating = ageRating;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public int getRuntime() {
    return runtime;
  }

  public void setRuntime(int runtime) {
    this.runtime = runtime;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }
}
