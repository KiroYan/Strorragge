package com.restaurant.ordermanager.dao;

import java.util.List;
import com.restaurant.ordermanager.domain.User;

public interface UserDAO {
	
	public void addUser(User user);
	
	public User getUser(String name);
	
	public void removeUser(Integer id);
	
	public List<User> listUser();

}
