package org.example.services;

import java.util.ArrayList;
import java.util.List;
import org.example.DAO.MovieDAO;
import org.example.DAO.MovieTypeDAO;
import org.example.DAO.TypeDAO;
import org.example.entities.Movie;
import org.example.entities.MovieType;
import org.example.entities.Type;

public class MovieService {
  private final MovieDAO movieDAO;

  private final MovieTypeDAO movieTypeDAO;

  private final TypeDAO typeDAO;

  public MovieService() {
    movieDAO = new MovieDAO();
    movieTypeDAO = new MovieTypeDAO();
    typeDAO = new TypeDAO();
  }

  public List<Movie> getMovieByType(String typeName) {
    List<Movie> movies = new ArrayList<>();
    Type type = typeDAO.getTypeByName(typeName);
    if (type == null) {
      System.out.println("Type not found");
      return null;
    }
    List<MovieType> movieTypes = movieTypeDAO.getMovieTypeByTypeId(type.getId());
    movieTypes.forEach(
        movieType -> movies.add(movieDAO.read(Movie.class, movieType.getMovie().getId())));
    return movies;
  }
  public List<Movie> getMoviesByCompany(String company) {
    List<Movie> movies = movieDAO.getMoviesByCompany(company);
    if(movies == null){
      System.out.println("There is no movie produced by this company");
      return null;
    }
    return movies;
  }
}
