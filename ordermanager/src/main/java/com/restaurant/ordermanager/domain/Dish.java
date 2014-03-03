package com.restaurant.ordermanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "dishes")
@JsonAutoDetect
public class Dish {
	@Id
    @Column(name = "dish_id")
    @GeneratedValue
    private Integer id;
	 
	@ManyToOne
	@JoinColumn(name = "type_id") 
	private DishType type;
	
	@Column(name = "dish_name")
	private String name;
	
	@Column(name = "price")
	private Integer price;
	
	public Dish(){}
	
	public Dish(DishType type, String name, Integer price) {
		this.type = type;
		this.name = name;
		this.price = price;
	}
	
	@JsonProperty
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@JsonProperty
	public DishType getType() {
		return type;
	}

	public void setType(DishType type) {
		this.type = type;
	}
	
	@Override
    public String toString() {
    	return name;
    }
	
	@Override
    public boolean equals(Object o) {
    	if(o == this)
    		return true;
    	if(!(o instanceof Dish))
    		return false;
    	return id.equals(((Dish)o).getId());
    }
}
