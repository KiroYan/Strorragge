package com.task.newsportal.dao;

import java.util.List;

import com.task.newsportal.domain.User;



public interface UserDAO {
	
	public void addUser(User user);
	
	public User getUser(Integer id);
	
	public User getUserByLogin(String login);

    public List<User> listUser();

    public void removeUser(Integer id);

}
