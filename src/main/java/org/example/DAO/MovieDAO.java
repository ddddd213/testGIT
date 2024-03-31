package org.example.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Movie;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.mapping.List;
import org.hibernate.query.Query;



public class MovieDAO implements BaseDAO<Movie> {
    public MovieDAO() {
    }

    public boolean ifExistedByNameVn(String nameVn){
        Session session = null;
        try{
            session = HibernateUtils.getInstance().openSession();
            Query query = session.createNativeQuery("SELECT * FROM Movie m WHERE m.name_vn= :nameVn ", Movie.class);
            query.setParameter("nameEng", nameVn);
            Movie movie = (Movie) query.getSingleResult();
            return (movie!=null);
        } finally {
            if(session!=null){
                session.close();
            }

        }
    }

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
