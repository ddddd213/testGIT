package org.example.DAO;

import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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
//            T t = session.get(cl, id);
//            return t;
        } finally {
            if (session != null) {
                session.close();
            }
            System.out.println("Giang's commit");
        }
    }

    /**
     * Updates an existing entity of type T in the database.
     *
     * @param entity The entity object with updated data.
     * @return The updated entity object.
     */
    default T update(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return entity;
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

    /**
     * read all an entity of type T from the database .
     */
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

    default void display1() {
        System.out.println("Giangnvt1");
        System.out.println("Thay đổi display 1 tại nhánh feature: 1");

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


}
