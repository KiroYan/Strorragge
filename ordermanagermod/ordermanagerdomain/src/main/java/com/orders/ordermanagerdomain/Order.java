package com.orders.ordermanagerdomain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
    @Column(name = "order_id")
    @GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id") 
	User user;
	
	@Column(name = "date")
	Timestamp date;
	
	@ManyToMany
    @JoinTable(name="dish_order", 
                joinColumns={@JoinColumn(name="order_id")}, 
                inverseJoinColumns={@JoinColumn(name="dish_id")})
	private Set<Dish> dishes = new HashSet<Dish>();

	public Order(){}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	public Set<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	public Integer getTotalPrice() {
		int total = 0;
		for(Dish dish:dishes) {
			total += dish.getPrice();
		}
		return total;
	}


}
