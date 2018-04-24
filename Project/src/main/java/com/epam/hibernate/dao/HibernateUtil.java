package com.epam.hibernate.dao;

import com.epam.entities.Message;
import com.epam.entities.User;
import java.util.Locale;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static SessionFactory sessionFactory;

  private static SessionFactory buildSessionFactory() {
    try {
      Locale.setDefault(Locale.ENGLISH);
      Configuration configuration = new Configuration().configure();
      configuration.addAnnotatedClass(User.class);
      configuration.addAnnotatedClass(Message.class);
      StandardServiceRegistryBuilder builder;
      builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
    return sessionFactory;
  }

  /**
   * Получение экземпляра фабрики сессии . 
   */
  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      sessionFactory = buildSessionFactory();
    }
    return sessionFactory;
  }
}
