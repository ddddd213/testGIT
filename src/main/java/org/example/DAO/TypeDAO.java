package org.example.DAO;

import org.example.entities.Type;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;

public class TypeDAO implements BaseDAO<Type> {
    public TypeDAO() {
    }

    public Type getTypeByName(String name) {
        Session session = null;
        try {
            session = HibernateUtils.getInstance().openSession();
            String hql = "from Type where typeName = :name";
            return session.createQuery(hql, Type.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
