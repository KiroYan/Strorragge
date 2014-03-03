package com.restaurant.ordermanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.restaurant.ordermanager.dao.OrderDAO;
import com.restaurant.ordermanager.domain.Order;

@Service
public class OrderService {
	@Autowired
    private OrderDAO orderDAO;
 
    @Transactional
    public void addOrder(Order order) {
    	orderDAO.addOrder(order);
    }
 
    @Transactional
    public List<Order> listOrder() {
 
        return orderDAO.listOrder();
    }
 
    @Transactional
    public void removeOrder(Integer id) {
    	orderDAO.removeOrder(id);
    }

    @Transactional
	public Order getOrder(Integer id) {
		return orderDAO.getOrder(id);
	}
}
