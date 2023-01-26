package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> db1;
    private HashMap<String, Director> db2;
    private HashMap<String, List<String>> db3;

    // Pair is : DirectorName, List of Movie Names

    public MovieRepository() {
        this.db1 = new HashMap<>();
        this.db2 = new HashMap<>();
        this.db3 = new HashMap<>();
    }

    public void addMovie(Movie movie){
       db1.put(movie.getName(), movie);
    }

    public void addDirector(Director director){
        db2.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName){

        if(db1.containsKey(movieName) && db2.containsKey(directorName)){

            List<String> movieList = new ArrayList<>();
            if(db3.containsKey(directorName)){
                movieList = db3.get(directorName);
            }

            movieList.add(movieName);
            db3.put(directorName, movieList);
        }
    }

    public Movie getMovieByName(String movieName){
        return db1.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return db2.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movieList = new ArrayList<>();
        if(db3.containsKey(directorName)) movieList = db3.get(directorName);
        return movieList;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(db1.keySet());
    }

    public void deleteDirectorByName(String directorName){
        List<String> movieList = new ArrayList<>();
        if(db3.containsKey(directorName)){
            movieList = db3.get(directorName);

            for(String movieName : movieList){
                if(db1.containsKey(movieName)) db1.remove(movieName);
            }

            db3.remove(directorName);
        }

        if(db2.containsKey(directorName)) db2.remove(directorName);
    }

    public void deleteAllDirectors(){
        // Deleting directors database
        db2.clear();

        HashSet<String> movieSet = new HashSet<>();
        for(String directorName : db3.keySet()){

            for(String movieName : db3.get(directorName)){
                movieSet.add(movieName);
            }
        }

        // Deleting movie from db1
        for(String movieName : movieSet){
            if(db1.containsKey(movieName)){
                db1.remove(movieName);
            }
        }

        // Clearing the pair database
        db3.clear();
    }
}
