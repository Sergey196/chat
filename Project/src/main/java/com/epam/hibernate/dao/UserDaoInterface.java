package com.epam.hibernate.dao;

import com.epam.entities.User;
import java.util.List;

public interface UserDaoInterface {
  void save(User loginingUser);
  
  void update(User logoutingUser);
  
  User findByNick(String nick);
  
  User findByNickAndPassword(String nick, String password);
  
  List<User> findAllLogged();
}
