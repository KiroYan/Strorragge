package com.orders.ordermanagerdao;

import java.util.List;

import com.orders.ordermanagerdomain.Order;

public interface OrderDAO {

	public void addOrder(Order order);
	
	public Order getOrder(Integer id);
	
	public void removeOrder(Integer id);
	
	public List<Order> listOrder();
	
	public List<Order> listOrder(int begin, int size);
	
	public List<Order> listOrder(int begin, int size, String field, boolean asc);
	
	public Long ordersCount();
	
}
