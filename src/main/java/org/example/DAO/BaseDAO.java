package org.example.DAO;

import java.util.List;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public interface BaseDAO<T> {
    /**
     * Creates a new entity of type T and persists it in the database.
     *
     * @param entity The entity object to be created.
     * @return The created entity object with its ID (if applicable).
     */
    default boolean create(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (session != null)
                session.close();
        }
  /**
   * Creates a new entity of type T and persists it in the database.
   *
   * @param entity The entity object to be created.
   * @return The created entity object with its ID (if applicable).
   */
  default boolean create(T entity) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtils.getInstance().openSession();
      session.beginTransaction();
      session.persist(entity);
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      return false;
    } finally {
      if (session != null) session.close();
    }
  }

    /**
     * Retrieves an entity of type T from the database by its ID.
     *
     * @param id The ID of the entity to be retrieved.
     * @return The entity object with the specified ID, or null if not found.
     */
    default T read(Class<T> cl, String id) {
        Session session = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            T t = session.get(cl, id);
            return t;
        } finally {
            if (session != null) {
                session.close();
            }
            System.out.println("Giang's commit");
        }
  /**
   * Retrieves an entity of type T from the database by its ID.
   *
   * @param id The ID of the entity to be retrieved.
   * @return The entity object with the specified ID, or null if not found.
   */
  default T read(Class<T> cl, String id) {
    Session session = null;
    try {
      session = HibernateUtils.getInstance().openSession();
      T t = session.get(cl, id);
      return t;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * Updates an existing entity of type T in the database.
   *
   * @param entity The entity object with updated data.
   * @return The updated entity object.
   */
  default T update(T entity) {
    try (Session session = HibernateUtils.getInstance().openSession()) {
      Transaction transaction = session.beginTransaction();
      T updatedEntity = (T) session.merge(entity);
      transaction.commit();
      return updatedEntity;
    } catch (Exception e) {
      throw new RuntimeException("Error updating entity: " + e.getMessage(), e);
    }
  }

    /**
     * Deletes an entity of type T from the database by its ID.
     *
     * @param id The ID of the entity to be deleted, class entity.
     */
    default void delete(String id, Class<T> cl) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            transaction = session.beginTransaction();
  /**
   * Deletes an entity of type T from the database by its ID.
   *
   * @param id The ID of the entity to be deleted, class entity.
   */
  default void deleteByID(String id, Class<T> cl) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtils.getInstance().openSession();
      transaction = session.beginTransaction();

      T entity = session.get(cl, id);
      if (entity != null) {
        session.delete(entity);
      }

      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw e;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  default void deleteByName(String name, Class<T> cl) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtils.getInstance().openSession();
      transaction = session.beginTransaction();

      T entity = getByName(name, cl);
      if (entity != null) {
        session.delete(entity);
      }

      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw e;
    } finally {
      if (session != null) {
        session.close();
      }
    }

    default void display1() {
        System.out.println("Giangnvt1");
        System.out.println("Thay đổi display 1 tại nhánh feature: 1");
  }

  default T getByName(String name, Class<T> cl) {
    Session session = null;
    try {
      session = HibernateUtils.getInstance().openSession();
      Query<T> query =
          session.createQuery("FROM " + cl.getSimpleName() + " WHERE name = :name", cl);
      query.setParameter("name", name);
      return query.uniqueResult();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

    // create deleteByID
    default void deleteByID(String id, Class<T> cl) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            transaction = session.beginTransaction();

            T entity = session.get(cl, id);
            if (entity != null) {
                session.delete(entity);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

  default T getByID(String id, Class<T> cl) {
    Session session = null;
    try {
      session = HibernateUtils.getInstance().openSession();
      Query<T> query = session.createQuery("FROM " + cl.getSimpleName() + " WHERE id = :id", cl);
      query.setParameter("id", id);
      return query.uniqueResult();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /** read all an entity of type T from the database. */
  default List<T> getAll(Class<T> cl) {
    Session session = null;
    try {
      session = HibernateUtils.getInstance().openSession();
      Query<T> query = session.createQuery("FROM " + cl.getSimpleName(), cl);
      return query.list();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  default T getByID(Class<T> cl, String id) {
    try (Session session = HibernateUtils.getInstance().openSession()) {
      return session.get(cl, id);
    }
  }

  default void display1() {
    System.out.println("Giangnvt1");
    System.out.println("Thay đổi display 1 tại nhánh feature: 1");
  }

  //
}
