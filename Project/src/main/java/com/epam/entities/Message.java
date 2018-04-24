package com.epam.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_MESSAGES")
  @SequenceGenerator(name = "SEQUENCE_MESSAGES", sequenceName = "SEQUENCE_MESSAGES", allocationSize = 1)
  @Column(name = "id_message")
  
  private Integer idMessage;

  @Column(name = "user_nick")
  private String user;

  @Column(name = "date_time")
  private Date dateTime;

  @Column(name = "action")
  private String action;

  @Column(name = "message")
  private String message;

  public void setId_message(Integer idMessage) {
    this.idMessage = idMessage;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public void setDate_time(Date dateTime) {
    this.dateTime = dateTime;
  }
  
  public void setAction(String action) {
    this.action = action;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getId_Message() {
    return this.idMessage;
  }
  
  public String getUser() {
    return this.user;
  }
  
  public Date getDate_time() {
    return this.dateTime;
  }
  
  public String getAction() {
    return this.action;
  }
  
  public String getMessage() {
    return this.message;
  }

  @Override
  public String toString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    StringBuilder str = new StringBuilder();
    str.append(dateFormat.format(getDate_time()));
    str.append(" ");
    str.append(getUser());
    str.append(" ");
    str.append(getMessage());

    return str.toString();
  }
}
