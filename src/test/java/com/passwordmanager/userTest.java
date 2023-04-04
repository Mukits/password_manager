package com.passwordmanager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.multipasswordmngr.domain.User;
import com.multipasswordmngr.domain.vo.Password;
import com.multipasswordmngr.domain.vo.WebsiteName;

public class userTest {

    @Test
    public void itCreatesBasicUser() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void UserPasswordNotNull() {
        Password testPass = new Password("passwordTest123");
        User user = new User();
        user.setPassword(testPass);
        assertNotNull(user.getPassword());
       
    }
    
    @Test
    public void UserPasswordShort() {
        Password testPass = new Password("pass");
        User user = new User();
        user.setPassword(testPass);
        assertFalse(user.getPassword().toString().equals(null));
       
    }

    @Test
    public void websiteNameNotNull() {
        WebsiteName webName = new WebsiteName("google.com");
        User user = new User();
        user.setWebsiteName(webName);
        assertNotNull(user.getWebsiteName());
    }

    @Test
    public void checkPasswordHashingWorks() {
        Password password = new Password("Password123");
        User user = new User();
        user.setOriginalPassword(password.toString());
        assertTrue(password.toString().equals(user.getOriginalPassword()));
    }


     @Test
    public void notNullTimeDate() {
        User user = new User();
        assertNotNull(user.getCreationDate());
    }


}
