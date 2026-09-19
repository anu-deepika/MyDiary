package com.twg.spring.mydiary.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twg.spring.mydiary.dao.UserDaoInterface;
import com.twg.spring.mydiary.entities.User;

@Service
public class UserBusinessInterfaceImpl implements UserBusinessInterface {

    @Autowired
    private UserDaoInterface userInterface;
    
    public UserDaoInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserDaoInterface userInterface) {
        this.userInterface = userInterface;
    }
    
   
    @Transactional // Fixes the 500 error for saving
    public void save(User user) {
        userInterface.save(user);
    }

    
    @Transactional // Fixes the 500 error for updating
    public void update(User user) {
        userInterface.update(user);
    }

  
    @Transactional // Fixes the 500 error for deleting
    public void delete(User user) {
        userInterface.delete(user);
    }

    
    @Transactional(readOnly = true)
    public User findById(int id) {
        return userInterface.findById(id);
    }
    
   
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userInterface.findAll();
    }

  
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        // FIXED: This was returning null, which would break the login page!
        return userInterface.findByUsername(username);
    }

}