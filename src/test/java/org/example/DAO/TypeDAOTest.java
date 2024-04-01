package org.example.DAO;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entities.Type;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TypeDAOTest {
  private static TypeDAO typeDao;
  private static Type type1;
  private static Type type2;

  @BeforeAll
  static void beforeAll() {
    typeDao = new TypeDAO();
  }

  @BeforeEach
  public void beforeEach() {
    type1 = new Type();
    type1.setTypeName("type11");
    type1.setDescription("Type 1 description");

    type2 = new Type();
    type2.setTypeName("type12");
    type2.setDescription("Type 2 description");
  }

  @AfterEach
  public void afterEach() {
    try (Session session = HibernateUtils.getInstance().openSession()) {
      session.beginTransaction();
      session.createMutationQuery("DELETE FROM MovieType").executeUpdate();
      session.createMutationQuery("DELETE FROM Type").executeUpdate();
      session.getTransaction().commit();
    }
  }

  @Test
  void testUpdate() {
    Type insertedType = typeDao.update(type1);
    assertNotNull(insertedType.getId());

    insertedType.setTypeName("Updated Type Name");
    insertedType.setDescription("Updated Type Description");
    Type updatedType = typeDao.update(insertedType);

    assertEquals("Updated Type Name", updatedType.getTypeName());
    assertEquals("Updated Type Description", updatedType.getDescription());
  }
}
