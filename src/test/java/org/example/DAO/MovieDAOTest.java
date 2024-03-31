package org.example.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Movie;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieDAOTest {
  static MovieDAO movieDAO;
  static Movie movie1;
  static Movie movie2;
  private final Logger logger = LogManager.getLogger(this.getClass());

  @BeforeAll
  static void beforeAll() {
    movieDAO = new MovieDAO();
  }

  @BeforeEach
  public void beforeEach() {
    movie1 =
        new Movie(
            "Movie001",
            "NinhNV",
            "NinhNV and his girlfriend",
            "GiangNVT",
            2.5,
            LocalDate.of(2017, 12, 23),
            LocalDate.of(2018, 2, 21),
            "JapaneseTheater",
            "Version 02",
            "Ninh's trouble",
            "Noi buon cua Ninh",
            "Large Image",
            "Small Image");

    movie2 =
        new Movie(
            "Movie002",
            "NinhNV",
            "NinhNV and his girlfriend",
            "GiangNVT",
            2.5,
            LocalDate.of(2017, 12, 23),
            LocalDate.of(2018, 2, 21),
            "JapaneseTheater",
            "Version 02",
            "Handsome Guy and Lucky girl",
            "Anh chang may man",
            "Large Image",
            "Small Image");
  }

  @AfterEach
  public void afterEach() {
    try (Session session = HibernateUtils.getInstance().openSession()) {
      session.beginTransaction();
      session.createMutationQuery("DELETE FROM Movie").executeUpdate();
      session.getTransaction().commit();
    }
  }

  @Test
  void testCreate() {
    assertTrue(movieDAO.create(movie1));
    logger.info("Successfull!");
  }

  @Test
  void testGetAll() {
    movieDAO.create(movie1);
    movieDAO.create(movie2);
    assertNotNull(movieDAO.getAll(Movie.class));
  }

  @Test
  void testGetById() {
    movieDAO.create(movie1);
    assertNotNull(movieDAO.read(Movie.class, movie1.getId()));
  }

  @Test
  void testUpdate() {
    movieDAO.create(movie1);

    Movie insertedMovie = movieDAO.read(Movie.class, movie1.getId());
    insertedMovie.setMovieNameVn("Tuan cui");
    insertedMovie.setSmallImage("new link");

    movieDAO.update(insertedMovie);

    Movie updatedMovie = movieDAO.read(Movie.class, insertedMovie.getId());
    assertNotNull(updatedMovie);
    assertEquals(insertedMovie.getId(), updatedMovie.getId());
    assertEquals(insertedMovie.getMovieNameVn(), updatedMovie.getMovieNameVn());
    assertEquals(insertedMovie.getSmallImage(), updatedMovie.getSmallImage());
  }

  @Test
  public void testGetMovies() {
    // prepare data
    Movie movie3 =
        Movie.builder()
            .actor("Timothée Chalamet, Zendaya, Rebecca Ferguson, Javier Bardem")
            .content(
                "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.")
            .director("Denis Villeneuve")
            .duration(166)
            .movieNameEng("Dune: Part Two")
            .movieNameVn("Hành Tinh Cát: Phần 2")
            .smallImage(
                "https://www.imdb.com/title/tt15239678/mediaviewer/rm4259595777/?ref_=tt_ov_i")
            .build();

    Movie movie4 =
        Movie.builder()
            .actor("Rebecca Hall, Brian Tyree Henry, Dan Stevens, Kaylee Hottle")
            .content(
                "Two ancient titans, Godzilla and Kong, clash in an epic battle as humans unravel their intertwined origins and connection to Skull Island's mysteries.")
            .director("Adam Wingard")
            .duration(115)
            .movieNameEng("Godzilla x Kong: The New Empire")
            .movieNameVn("Godzilla x Kong: Đế Chế Mới")
            .smallImage(
                "https://www.imdb.com/title/tt14539740/mediaviewer/rm2293972993/?ref_=tt_ov_i")
            .build();

    movieDAO.create(movie1);
    movieDAO.create(movie2);

    // Test pagination
    List<Movie> movies = movieDAO.getMovies(1, 2);

    // Assert result
    assertEquals(2, movies.size());
    // check movie1 in list
    assertNotNull(
        movies.stream()
            .filter(movie -> movie.getId().equals(movie1.getId()))
            .findFirst()
            .orElse(null));
    // check movie2 in list
    assertNotNull(
        movies.stream()
            .filter(movie -> movie.getId().equals(movie2.getId()))
            .findFirst()
            .orElse(null));
  }
}
