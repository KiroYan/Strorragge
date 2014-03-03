package com.restaurant.ordermanager.dao;

import java.util.List;
import com.restaurant.ordermanager.domain.Order;

public interface OrderDAO {

	public void addOrder(Order order);
	
	public Order getOrder(Integer id);
	
	public void removeOrder(Integer id);
	
	public List<Order> listOrder();
	
}
