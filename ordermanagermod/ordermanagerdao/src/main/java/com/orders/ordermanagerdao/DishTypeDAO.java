package com.orders.ordermanagerdao;

import java.util.List;

import com.orders.ordermanagerdomain.DishType;

public interface DishTypeDAO {
	
	public void addDishType(DishType type);
	
	public DishType getDishType(Integer id);
	
	public void removeDishType(Integer id);
	
	public List<DishType> listDishType();
	 
}
