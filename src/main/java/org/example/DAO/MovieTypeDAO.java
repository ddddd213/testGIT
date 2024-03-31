package org.example.DAO;

import java.util.List;
import org.example.entities.MovieType;
import org.example.entities.MovieTypePK;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class MovieTypeDAO implements BaseDAO<MovieType> {
  private final HibernateUtils hibernateUtils;
  private final MovieDAO movieDAO;
  private final TypeDAO typeDAO;

  public MovieTypeDAO() {
    this.hibernateUtils = HibernateUtils.getInstance();
    this.movieDAO = new MovieDAO();
    this.typeDAO = new TypeDAO();
  }

  public List<MovieType> getAll() {
    try (Session session = hibernateUtils.openSession()) {
      Query<MovieType> query = session.createQuery("FROM MovieType ");
      return query.list();
    } catch (Exception e) {

      return null;
    }
  }

  public MovieType getById(MovieTypePK id) {
    try (Session session = hibernateUtils.openSession()) {
      return session.get(MovieType.class, id);
    } catch (Exception e) {
      return null;
    }
  }

  public boolean create(MovieType movieType) {
    try (Session session = hibernateUtils.openSession()) {
      session.beginTransaction();
      session.persist(movieType);
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public MovieType update(MovieType movieType) {
    try (Session session = hibernateUtils.openSession()) {
      session.beginTransaction();
      session.merge(movieType);
      session.getTransaction().commit();
      return movieType;
    } catch (Exception e) {

      return null;
    }
  }

  public boolean delete(MovieTypePK id) {
    try (Session session = hibernateUtils.openSession()) {
      MovieType deletedMovieType = getById(id);
      session.beginTransaction();
      session.remove(deletedMovieType);
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {

      return false;
    }
  }

  public List<MovieType> getMovieTypeByTypeId(int typeId) {
    Session session = null;
    try {
      session = HibernateUtils.getInstance().openSession();
      String hql = "from MovieType where id = :typeId";
      return session.createQuery(hql, MovieType.class).setParameter("typeId", typeId).list();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
}
