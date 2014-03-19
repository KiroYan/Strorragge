package com.task.newsportal.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAGS")

public class Tag {
	@Id
	@GeneratedValue
	@Column(name = "TAG_ID")
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@ManyToMany(mappedBy = "tags")
	private List<News> news = new ArrayList<News>();

	public Tag(){}

	public Tag(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(!(obj instanceof Tag))
			return false;
		
		return id == ((Tag)obj).getId();
		
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}