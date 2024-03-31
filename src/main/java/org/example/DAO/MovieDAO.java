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
    public List<Movie> getMoviesByCompany(String company) {
        Session session = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            String hql = "FROM Movie m WHERE m.movieProductionCompany = :company";
            Query<Movie> query = session.createQuery(hql, Movie.class);
            query.setParameter("company", company);
            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }



        }
    }
}
