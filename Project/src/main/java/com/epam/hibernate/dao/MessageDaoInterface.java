package com.epam.hibernate.dao;

import com.epam.entities.Message;
import java.util.List;

public interface MessageDaoInterface {
  void save(Message message);
  
  List<Message> findLastTen();
}
