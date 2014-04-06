package com.task.newsportal.service;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.newsportal.dao.CommentDAO;
import com.task.newsportal.domain.Comment;

@Service
public class CommentService {
	
	@Autowired
    private CommentDAO commentDAO;
	
	@Transactional
	public void addComment(Comment comment) {
		commentDAO.addComment(comment);
	}

	@Transactional
	public List<Comment> listComment() {
		return commentDAO.listComment();
	}

	@Transactional
	public void removeComment(Integer id) {
		commentDAO.removeComment(id);
	}
}
