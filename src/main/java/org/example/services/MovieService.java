package org.example.services;

import org.example.DAO.MovieDAO;
import org.example.entities.Movie;

public class MovieService {
    private MovieDAO movieDAO;
    MovieService(){
        movieDAO = new MovieDAO();
    }
    public void showMovieByName(String name){
        List<Movie> movieList = movieDAO.findMovieByName(name);
        for (Movie movie: movieList) {
            System.out.println(movie.getMovieNameVn());
        }
    }
}
