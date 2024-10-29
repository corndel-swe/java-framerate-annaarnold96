package com.corndel.framerate;

import com.corndel.framerate.models.Movie;
import com.corndel.framerate.repositories.MovieRepository;
import com.corndel.framerate.repositories.ReviewRepository;
import io.javalin.http.HttpStatus;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.Map;

public class App {
  public static void main(String[] args) {
    var javalin = createApp();
    javalin.start(8081);
  }

  public static Javalin createApp() {
    var app = Javalin.create(
        config -> {
          config.staticFiles.add("/public", Location.CLASSPATH);

          var resolver = new ClassLoaderTemplateResolver();
          resolver.setPrefix("/templates/");
          resolver.setSuffix(".html");
          resolver.setTemplateMode("HTML");

          var engine = new TemplateEngine();
          engine.setTemplateResolver(resolver);

          config.fileRenderer(new JavalinThymeleaf(engine));
        });

    // Shows a list of all movies
    app.get("/", ctx -> {
      var movies = MovieRepository.findAll();
        ctx.render("index.html", Map.of("m",movies));
    });

    // Gets movie details (inc review) when given an id
    app.get("/movie/{movieId}", ctx -> {
      var id = Integer.parseInt(ctx.pathParam("movieId"));
        var movies = MovieRepository.findById(id);
        ctx.render("index.html", Map.of("m",movies));
    });

    // shows films with the inputted genre
    app.get("/movies/{genre}", ctx -> {
        var genre = ctx.pathParam("genre");
        var users = MovieRepository.findByGenre(genre);
        ctx.json(users);
    });

    // Show all reviews
    app.get("/reviews",
        ctx -> {
        var reviews = ReviewRepository.allReviews();
        ctx.render("showReviews.html", Map.of("r",reviews));
//                ctx.render("review.html");
            });

    // Leave a review for a movie with a given id
    app.get("/review/{movieId}", ctx ->{
        ctx.render("review.html");

    });

    // Post review to database
    app.post("/review/{movieId}", ctx -> {
        var id = Integer.parseInt(ctx.pathParam("movieId"));
          // get the rating
          int rating = ctx.formParamAsClass("rating", Integer.class).get();

          // get review
          String review = ctx.formParamAsClass("review", String.class).get();

        var insertReview = ReviewRepository.reviewById(id,rating,review);
          ctx.result(String.valueOf(insertReview));
      });

    return app;
  }
}
