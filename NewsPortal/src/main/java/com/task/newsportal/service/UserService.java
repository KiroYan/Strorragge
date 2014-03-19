package com.task.newsportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.newsportal.dao.UserDAO;
import com.task.newsportal.domain.User;

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
	public User getUser(Integer id) {
		return userDAO.getUser(id);
	}
	@Transactional
	public User getUserByLogin(String login){
		return userDAO.getUserByLogin(login);
	}

}
