package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response =  movieService.addMovie(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String response =  movieService.addDirector(director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
        String response =  movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        Movie movie = movieService.getMovieByName(movieName);
        if(movie==null){
            return new ResponseEntity<>("Invalid request", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
        Director director = movieService.getDirectorByName(directorName);
        if(director==null){
            return new ResponseEntity<>("Invalid request", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(director, HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> movieList = new ArrayList<>();
        movieList = movieService.getMoviesByDirectorName(directorName);

        return new ResponseEntity<>(movieList, HttpStatus.ACCEPTED);

    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> movieList = new ArrayList<>();
        movieList = movieService.findAllMovies();

        return new ResponseEntity<>(movieList, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String directorName){
        String response = movieService.deleteDirectorByName(directorName);
        if(response.equals("Director is not present in data base")){
            return new ResponseEntity<>("Invalid request", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);

    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String response = movieService.deleteAllDirectors();
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
