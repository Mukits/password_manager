package com.multipasswordmngr.domain;

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

import org.springframework.format.annotation.DateTimeFormat;


import com.multipasswordmngr.domain.converters.PWDAttributeConverter;
import com.multipasswordmngr.domain.converters.WebNameAttributeConverter;
import com.multipasswordmngr.domain.vo.Password;

import com.multipasswordmngr.domain.vo.WebsiteName;

//DEFINE MAIN ENTITY
@Entity
@Table(name = "users")
public class User {
    // id will be unique for earch website
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    // column that will hold the name of the user
    @NotEmpty(message = "Username is mandatory")
    @Column(name = "username", nullable = false)
    private String name;

    // column that will hold the originalPassword values before encryption

    @Column(name = "originalPassword")
    private String originalPassword;
    
    // column that will hold the password values

    @NotEmpty(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    @Convert(converter = PWDAttributeConverter.class)
    private Password password;
    
    // column that will hold the website names
    @NotEmpty(message = "WebsiteName is mandatory")
    @Column(name = "websiteName", nullable = false)
    @Convert(converter = WebNameAttributeConverter.class)
    private WebsiteName websiteName;
    
    // this column will hold the creation date of the password in local time
   
    @Column(name="creationDate")
    private LocalDateTime creationDate;

    // construction of User that initializes only the value of creationDate
    public User() {
        creationDate = LocalDateTime.now();
        
    }
   
    //User constructor that initializes all the columns 
    public User(Integer id, String name, Password password, WebsiteName websiteName) {
        this.id = id;
        this.name= name;
        this.password = password;
        this.websiteName = websiteName;
        creationDate = LocalDateTime.now();
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
        return this.name;
    }
     // gets the original password
    public String getOriginalPassword() {
        return originalPassword;
    }
    //sets the originalPassword
    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public Password getPassword() {
        return password;
    }

    public WebsiteName getWebsiteName() {
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

    public void setWebsiteName(WebsiteName websiteName) {
        this.websiteName = websiteName;
    }

}
