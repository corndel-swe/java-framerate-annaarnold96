package com.corndel.framerate.repositories;

import com.corndel.framerate.DB;
import com.corndel.framerate.models.Movie;
import com.corndel.framerate.models.Review;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewRepository {
  public static List<Review> allReviews() throws SQLException {
    var query = "SELECT reviews.*, movies.title FROM REVIEWS INNER JOIN movies ON movies.id = reviews.movieId";

    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query)) {
//      stmt.setInt(1, movieId);
      try (var rs = stmt.executeQuery()) {
        var reviews = new ArrayList<Review>();
        while (rs.next()) {
          var review = new Review();
          review.setId(rs.getInt("id"));
          review.setMovieId(rs.getInt("movieId"));
          review.setCreatedAt(rs.getLong("createdAt"));
          review.setContent(rs.getString("content"));
          review.setRating(rs.getInt("rating"));
          review.setMovieTitle(rs.getString("title"));
          reviews.add(review);

        }
        return reviews;
      }
    }
  }

  public static Review reviewById(int id, int rating, String review) throws SQLException {
    var query = "INSERT INTO reviews (movieId, content, rating) VALUES (?, ?, ?) RETURNING *";

    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query)) {
      stmt.setInt(1,id);
      stmt.setString(2, review);
      stmt.setInt(3, rating);


      try (var rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        var reviewId = rs.getInt("id");
        var movieId = rs.getInt("movieId");
        var createdAt = rs.getLong("createdAt");
        var rsRating = rs.getInt("rating");
        var content = rs.getString("content");


        return new Review(reviewId,movieId,createdAt,rsRating,content);
      }
    }
  }
}
