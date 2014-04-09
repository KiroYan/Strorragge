package com.orders.ordermanagerdao;

import java.util.List;

import com.orders.ordermanagerdomain.User;

public interface UserDAO {
	
	public void addUser(User user);
	
	public User getUser(String name);	
	
	public User getCurrentUser();
	
	public void removeUser(Integer id);
	
	public List<User> listUser();

}
