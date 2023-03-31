package com.passwordmanager.domain;

import com.passwordmanager.domain.converters.PWDAttributeConverter;
import com.passwordmanager.domain.vo.Password;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;

//DEFINE MAIN ENTITY
@Entity
@Table(name = "users")
public class User {
    // id will be unique for earch website
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    // column that will hold the name of the user

    @Column(length = 128, nullable = false)
    private String name;
    // column that will hold the originalPassword values before encryption

    @Column(name = "originalPassword")
    @Convert(converter = PWDAttributeConverter.class)
    private Password originalPassword;
    
    // column that will hold the password values

    @NotEmpty(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    @Convert(converter = PWDAttributeConverter.class)
    private Password password;
    
    // column that will hold the website names
  
    @Column(nullable = false)
    private String websiteName;
    
    // this column will hold the creation date of the password in local time
   
    @Column(name="creationDate")
    private LocalDateTime creationDate;

    // construction of User that initializes only the value of creationDate
    public User() {
        creationDate = LocalDateTime.now();
        
    }
   
    //User constructor that initializes all the columns 
    public User(Integer id, String name, Password password, String websiteName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.originalPassword = password;
        this.websiteName = websiteName;
        creationDate = LocalDateTime.now();
    }
    // gets the original password
    public Password getOriginalPassword() {
        return originalPassword;
    }

    // formats the creation date to dd-MM-yyyy HH:mm
    public String getCreationDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return creationDate.format(format);
    } 

    // gets the ID
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public Password getPassword() {
        return password;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

}
