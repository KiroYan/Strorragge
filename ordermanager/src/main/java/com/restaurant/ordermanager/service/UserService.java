package com.restaurant.ordermanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.restaurant.ordermanager.dao.UserDAO;
import com.restaurant.ordermanager.domain.User;

@Service
public class UserService {
	@Autowired
    private UserDAO userDAO;
 
    @Transactional
    public void addUser(User user) {
    	userDAO.addUser(user);
    }
    
    @Transactional
    public List<User> listUser() {
        return userDAO.listUser();
    }
    
    @Transactional
    public void removeUser(Integer id) {
    	userDAO.removeUser(id);
    }
    
    @Transactional
    public User getUser(String name) {
    	return userDAO.getUser(name);
    }
}
