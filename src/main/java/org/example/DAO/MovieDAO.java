package org.example.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Movie;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MovieDAO implements BaseDAO<Movie> {
    /**
     * Retrieves a paginated list of entities from the database.
     * @param pageNumber - The page number of the results to retrieve
     * @param pageSize - The maximum number of entities per page
     * @return A list of entities retrieved from the database
     */
    public List<Movie> getMovies(int pageNumber, int pageSize) {
        try (Session session = HibernateUtils.getInstance().openSession()) {
            // Create CriteriaBuilder
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Movie> criteriaQuery = builder.createQuery(Movie.class);
            Root<Movie> root = criteriaQuery.from(Movie.class);
            criteriaQuery.select(root);

            // Execute query with pagination
            Query<Movie> query = session.createQuery(criteriaQuery);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            List<Movie> movies = query.getResultList();

            return movies;
        }
    }
}
