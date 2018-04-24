package com.epam.hibernate.dao;

import com.epam.entities.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class UserDao implements UserDaoInterface {

  private Session currentSession;
  private Transaction currentTransaction;

  public Session openCurrentSession() {
    currentSession = getSessionFactory().openSession();
    return currentSession;
  }
  
  /**
   * Открытие сессии и транзакции. 
   */
  public Session openCurrentSessionwithTransaction() {
    currentSession = getSessionFactory().openSession();
    currentTransaction = currentSession.beginTransaction();
    
    return currentSession;
  }
  
  public void closeCurrentSession() {
    currentSession.close();
  }
  
  public void closeCurrentSessionwithTransaction() {
    currentTransaction.commit();
    currentSession.close();
  }
  
  private static SessionFactory getSessionFactory() {
    return HibernateUtil.getSessionFactory();
  }

  public Session getCurrentSession() {
    return currentSession;
  }

  public void setCurrentSession(Session currentSession) {
    this.currentSession = currentSession;
  }

  public Transaction getCurrentTransaction() {
    return currentTransaction;
  }

  public void setCurrentTransaction(Transaction currentTransaction) {
    this.currentTransaction = currentTransaction;
  }

  @Override
  public void save(User loginingUser) {
    openCurrentSessionwithTransaction();
    getCurrentSession().save(loginingUser);
    closeCurrentSessionwithTransaction();
  }

  @Override
  public void update(User logoutingUser) {
    openCurrentSessionwithTransaction();
    getCurrentSession().update(logoutingUser);
    closeCurrentSessionwithTransaction();
  }
  
  @Override
  public User findByNick(String nick) {
    openCurrentSessionwithTransaction();
    Query query = getCurrentSession().createQuery("from User where nick = :nick");
    query.setParameter("nick", nick);
    User user = (User) query.uniqueResult();
    closeCurrentSessionwithTransaction();
    return user; 
  }
  
  @Override
  public User findByNickAndPassword(String nick, String password) {
    openCurrentSessionwithTransaction();
    Query query;
    query = getCurrentSession().createQuery("from User where nick=:nick and password=:password");
    query.setParameter("nick", nick);
    query.setParameter("password", password);
    User user = (User) query.uniqueResult();
    closeCurrentSessionwithTransaction();
    return user; 
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> findAllLogged() {
    openCurrentSessionwithTransaction();
    List<User> list = getCurrentSession().createCriteria(User.class).list();
    return list;
  }
}
