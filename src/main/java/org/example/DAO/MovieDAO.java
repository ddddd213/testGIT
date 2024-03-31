package org.example.DAO;

import org.example.entities.Movie;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.mapping.List;
import org.hibernate.query.Query;


public class MovieDAO implements BaseDAO<Movie> {
    public List<Movie> findMovieByName(String movieName) {
        Session session = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            String hql = "FROM Movie m WHERE m.name = :movieName";
            Query<Movie> query = session.createQuery(hql, Movie.class);
            query.setParameter("movieName", movieName);
            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
