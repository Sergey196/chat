package com.epam.hibernate.dao;

import com.epam.entities.Message;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

public class MessageDao implements MessageDaoInterface {

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
    HibernateUtil.getSessionFactory().openSession();
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
  public void save(Message message) {
    openCurrentSessionwithTransaction();
    getCurrentSession().save(message);
    closeCurrentSessionwithTransaction();
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public List<Message> findLastTen() {
    openCurrentSessionwithTransaction();
    List<Message> list = null;
    int count = Integer.parseInt(getCurrentSession().createCriteria(Message.class)
        .setProjection(Projections.rowCount()).uniqueResult().toString());
    if (count >= 20) {
      list = (List<Message>) getCurrentSession()
      .createQuery("FROM Message ORDER BY idMessage asc")
      .setFirstResult(count - 10).list();
    } else if (count > 10 && count < 20) {
      list = (List<Message>) getCurrentSession().createQuery("FROM Message ORDER BY idMessage asc")
      .setFirstResult(count - 10).list();
    } else {
      list = (List<Message>) getCurrentSession().createQuery("FROM Message ORDER BY idMessage asc")
      .setFirstResult(0).list();
    }
    closeCurrentSessionwithTransaction();
    return list;
  }
}
