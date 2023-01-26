package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    HashMap<String, Movie> db1 = new HashMap<>();
    HashMap<String, Director> db2 = new HashMap<>();
    HashMap<Movie, Director> db3 = new HashMap<>();

    public String addMovie(Movie movie){
        if(movie==null){
            return "Invalid entry";
        }
        String name = movie.getName();
        db1.put(name, movie);

        return "Movie added successfully";
    }

    public String addDirector(Director director){
        if(director==null){
            return "Invalid entry";
        }
        String name = director.getName();
        db2.put(name, director);

        return "Director added successfully";
    }

    public String addMovieDirectorPair(String movieName, String directorName){
        if(!db1.containsKey(movieName)){
            return "Movie is not present in data base";
        }

        if(!db2.containsKey(directorName)){
            return "Director is not present in data base";
        }

        Movie movie = db1.get(movieName);
        Director director = db2.get(directorName);
        db3.put(movie, director);

        return "Entry added successfully";
    }

    public Movie getMovieByName(String movieName){
        if(!db1.containsKey(movieName)){
            return null;
        }
        return db1.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        if(!db2.containsKey(directorName)){
            return null;
        }
        return db2.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movieList = new ArrayList<>();

        for(Map.Entry<Movie, Director> entry : db3.entrySet()){
            Movie movie = entry.getKey();
            Director director = entry.getValue();

            if(director.getName().equals(directorName)){
                movieList.add(movie.getName());
            }
        }

        return movieList;
    }

    public List<String> findAllMovies(){
        List<String> movieList = new ArrayList<>();

        for(String movieName : db1.keySet()) {
            movieList.add(movieName);
        }
        return movieList;
    }

    public String deleteDirectorByName(String directorName){
        if(!db2.containsKey(directorName)){
            return "Director is not present in data base";
        }

        db2.remove(directorName);
        for(Map.Entry<Movie, Director> entry : db3.entrySet()){
            Movie movie = entry.getKey();
            Director director = entry.getValue();

            if(director.getName().equals(directorName)){
                db3.remove(movie);
            }
        }
        return "Entry successfully removed from data base";
    }

    public String deleteAllDirectors(){
        db2.clear();
        for(Movie movie : db3.keySet()){
            db1.remove(movie.getName());
        }

        db3.clear();

        return "Data base cleared";
    }
}
