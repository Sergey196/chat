package com.epam.hibernate.dao;

public class HibernateDaoFactory extends DaoFactory {

  @Override
  public UserDaoInterface getUserDao() {
    return new UserDao();
  }

  @Override
  public MessageDaoInterface getMessageDao() {
    return new MessageDao();
  }
}

