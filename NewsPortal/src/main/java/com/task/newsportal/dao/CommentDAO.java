package com.task.newsportal.dao;

import java.util.List;

import com.task.newsportal.domain.Comment;

public interface CommentDAO {
	
	public void addComment(Comment comment);

    public List<Comment> listComment();

    public void removeComment(Integer id);

}
