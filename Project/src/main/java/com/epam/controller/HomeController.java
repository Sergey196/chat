package com.epam.controller;

import com.epam.entities.Message;
import com.epam.entities.User;
import com.epam.hibernate.dao.DaoFactory;
import com.epam.hibernate.dao.MessageDaoInterface;
import com.epam.hibernate.dao.UserDaoInterface;
import com.epam.websocket.Greeting;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes(types = User.class)
public class HomeController {

  @Autowired
  private HttpServletRequest request;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    return "login";
  }

  /**
   * Вход.
   */
  @RequestMapping(value = "/reg", method = RequestMethod.POST)
  public ModelAndView user(User user, Model model) {

    DaoFactory dao = DaoFactory.getDaoFactory();
  
    UserDaoInterface userDao = dao.getUserDao(); 

    User loginUser = userDao.findByNickAndPassword(user.getNick(), user.getPassword());

    if (loginUser == null || loginUser.getStatus().equals("ONLINE")) {
      return new ModelAndView("login");
    } else {
      loginUser.setStatus("ONLINE");
      userDao.update(loginUser);
      model.addAttribute("user", loginUser);

      return new ModelAndView("chat", "dao", dao);
    }
  }

  @RequestMapping(value = "/registration_user", method = RequestMethod.GET)
  public String user(Model model) {
    return "registration_user";
  }

  /**
   * Регистрация.
   */
  @RequestMapping(value = "/registration_login", method = RequestMethod.POST)
  public ModelAndView saveUser(@RequestParam("photo") MultipartFile file, User user, Model model) {

    DaoFactory dao = DaoFactory.getDaoFactory();
    UserDaoInterface userDao = dao.getUserDao(); 

    if (!file.isEmpty() && (userDao.findByNick(user.getNick()) == null)) {
      try {
        byte[] bytes = file.getBytes();
 
        String dir = "resources/bootstrap/images/";
        String fileDirectory = request.getSession().getServletContext().getRealPath(dir);

        File fileImage = new File(fileDirectory + File.separator + user.getNick() + ".jpg");
 
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileImage));
        stream.write(bytes);
        stream.flush();
        stream.close();

        fileImage.renameTo(new File(fileDirectory + File.separator + user.getNick() + ".jpg"));

        user.setRole("USER");
        user.setStatus("ONLINE");
        user.setImage(dir + user.getNick() + ".jpg");
        userDao.save(user);
        model.addAttribute("user", user);

      } catch (Exception e) {
        throw new ExceptionInInitializerError(e);
      }
      return new ModelAndView("chat", "dao", dao);
    } else {
      return new ModelAndView("registration_user");
    }
  }
  
  /**
   * Выход.
   */
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public String logout(User user, SessionStatus status) {
    DaoFactory dao = DaoFactory.getDaoFactory();
    
    UserDaoInterface userDao = dao.getUserDao(); 

    User loginUser = userDao.findByNick(user.getNick());
    loginUser.setStatus("OFFLINE");
    userDao.update(loginUser);
    status.setComplete();

    return "login";
  }

  /**
   * Список сообщений.
   */
  @RequestMapping(value = "/messages_list", method = RequestMethod.POST)
  public @ResponseBody String addMessages() {

    StringBuilder str = new StringBuilder();

    DaoFactory dao = DaoFactory.getDaoFactory();

    MessageDaoInterface messageDao = dao.getMessageDao(); 

    List<Message> list = messageDao.findLastTen();

    for (Message message: list) {
      str.append(message);
      str.append("\n");
    }
    return Base64.getEncoder().encodeToString(str.toString().getBytes());
  }

  /**
   * Отправка сообщения.
   */
  @MessageMapping("/sent")
  @SendTo("/topic/greetings")
  public Greeting sent(Message message) throws Exception {

    Date date = new Date();
    message.setUser(message.getUser().replace("Hi, ", ""));
    message.setDate_time(date);
    message.setAction("SEND");

    DaoFactory dao = DaoFactory.getDaoFactory();

    MessageDaoInterface messageDao = dao.getMessageDao(); 

    messageDao.save(message);

    StringBuilder str = new StringBuilder();

    List<Message> list = messageDao.findLastTen();

    for (Message messageUnit: list) {
      str.append(messageUnit);
      str.append("\n");
    }

    return new Greeting(str.toString());
  }

  /**
   * Информация о пользователе.
   */
  @RequestMapping(value = "/loading_user", method = RequestMethod.POST)
  public @ResponseBody  String loadingUsers(@RequestBody String value) {
    DaoFactory dao = DaoFactory.getDaoFactory();

    UserDaoInterface userDao = dao.getUserDao(); 
    User user = userDao.findByNick(value.replace("=", ""));
    StringBuilder str = new StringBuilder();

    str.append(" name: ");
    str.append(user.getNick());
    str.append("\n");
    str.append(" telephone: ");
    str.append(user.getTelephone());
    str.append("\n");
    str.append(" status: ");
    str.append(user.getStatus());

    return str.toString();
  } 

  /**
   * Информация о пользователе.
   */
  @RequestMapping(value = "/loading_img", method = RequestMethod.POST)
  public @ResponseBody  String loadingImg(@RequestBody String value) {
    DaoFactory dao = DaoFactory.getDaoFactory();

    UserDaoInterface userDao = dao.getUserDao(); 
    User user = userDao.findByNick(value.replace("=", ""));

    return user.getImage();
  } 
}
