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

    app.get("/", ctx -> {
      var movies = MovieRepository.findAll();
        ctx.render("index.html", Map.of("m",movies));
    });

    app.get("/movie/{movieId}", ctx -> {
      var id = Integer.parseInt(ctx.pathParam("movieId"));
        var movies = MovieRepository.findById(id);
        ctx.render("index.html", Map.of("m",movies));
    });

    app.get("/movies/{genre}", ctx -> {
        var genre = ctx.pathParam("genre");
        var users = MovieRepository.findByGenre(genre);
        ctx.json(users);
    });

    app.get("/review",
            ctx -> {
                ctx.render("review.html");
            });

    app.post("/review/{movieId}", ctx ->{
        var id = Integer.parseInt(ctx.pathParam("movieId"));
        var user = MovieRepository.reviewById(id);
        ctx.status(HttpStatus.IM_A_TEAPOT).json(user);

    });

    return app;
  }
}
