package org.example.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
        movie1 = new Movie("Movie001",
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

        movie2 = new Movie("Movie002",
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

}