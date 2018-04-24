package com.epam.hibernate.dao;

public abstract class  DaoFactory {
  public abstract MessageDaoInterface getMessageDao();

  public abstract UserDaoInterface getUserDao();

  public static DaoFactory getDaoFactory() {
    return new HibernateDaoFactory(); 
  }
}
