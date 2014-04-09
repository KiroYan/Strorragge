package com.orders.ordermanagerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orders.ordermanagerdao.UserDAO;
import com.orders.ordermanagerdomain.User;

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

    @Transactional
	public User getCurrentUser() {
		return userDAO.getCurrentUser();
	}
    
    
}
