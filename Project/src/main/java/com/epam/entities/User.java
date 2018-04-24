package com.epam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_USERS")
  @SequenceGenerator(name = "SEQUENCE_USERS", sequenceName = "SEQUENCE_USERS", allocationSize = 1)
  
  @Column(name = "id_user")
  private Integer idUser;

  @Column(name = "nick")
  private String nick;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private String role;

  @Column(name = "status")
  private String status;

  @Column(name = "image")
  private String image;

  @Column(name = "telephone")
  private String telephone;

  public void setId_user(Integer idUser) {
    this.idUser = idUser;
  }
  
  public void setNick(String nick) {
    this.nick = nick;
  }
  
  public void setUser_name(String userName) {
    this.userName = userName;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public void setRole(String role) {
    this.role = role;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public void setImage(String image) {
    this.image = image;
  }
  
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public Integer getUserId() {
    return this.idUser;
  }
  
  public String getNick() {
    return this.nick;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public String getUser_name() {
    return this.userName;
  }
  
  public String getRole() {
    return this.role;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public String getImage() {
    return this.image;
  }
  
  public String getTelephone() {
    return this.telephone;
  }
}
