package org.example.DAO;

import org.example.entities.Movie;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class MovieDAO implements BaseDAO<Movie> {

    public boolean ifExistedByNameEng(String nameEng){
        Session session = null;
        try{
            session = HibernateUtils.getInstance().openSession();
            Query query = session.createNativeQuery("SELECT * FROM Movie m WHERE m.name_eng= :nameEng ", Movie.class);
            query.setParameter("nameEng", nameEng);
            Movie movie = (Movie) query.getSingleResult();
            return (movie!=null);
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }
}
