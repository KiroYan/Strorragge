package com.task.newsportal.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS") 
public class News {
	@Id
	@GeneratedValue
	@Column(name = "NEWS_ID")
	private int id;
	
	@Column(name = "TITLE")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Column(name = "DATE")
	private Timestamp date;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tag_news", joinColumns = { 
			@JoinColumn(name = "NEWS_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "TAG_ID", 
					nullable = false, updatable = false) })
	private List<Tag> tags = new ArrayList<Tag>();
	
	@Column(name = "TEXT")
	private String text;
	
	@OneToMany(mappedBy = "news", cascade = {CascadeType.ALL})
	private List<Comment> comments = new ArrayList<Comment>();
	
	public News(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
