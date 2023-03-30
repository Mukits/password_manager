package com.passmanager.password_manager.domain;



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

import com.passmanager.password_manager.domain.converters.PWDAttributeConverter;
import com.passmanager.password_manager.domain.vo.Password;

//DEFINE MAIN ENTITY
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String name;

    @NotEmpty(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    @Convert(converter = PWDAttributeConverter.class)
    private Password password;

    // CAN HAVE A PASSWORD FOR ONE WEBSITE
    @Column(nullable = false, unique = true)
    private String websiteName;
    
    @Column(name = "originalPassword")
    @Convert(converter = PWDAttributeConverter.class)
    private Password originalPassword;
   
    @Column(name="creationDate")
    private LocalDateTime creationDate;

    public User() {
        creationDate = LocalDateTime.now();
        
    }
   

    public User(Integer id, String name, Password password, String websiteName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.originalPassword = password;
        this.websiteName = websiteName;
        creationDate = LocalDateTime.now();
    }

    public Password getOriginalPassword() {
        return originalPassword;
    }

    public String getCreationDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return creationDate.format(format);
    } 
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
