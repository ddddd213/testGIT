package org.example.DAO;

import org.example.entities.MovieType;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class MovieTypeDAO implements BaseDAO<MovieType>{
    public MovieTypeDAO() {
    }

    public List<MovieType> getMovieTypeByTypeId(int typeId) {
        Session session = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            String hql = "from MovieType where id = :typeId";
            return session.createQuery(hql, MovieType.class)
                    .setParameter("typeId", typeId)
                    .list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
