package com.orders.ordermanagerdomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dish_types")
public class DishType {
	@Id
    @Column(name = "dish_type_id")
    @GeneratedValue
	private Integer id;
	
	@Column(name = "type_name")
	private String name;
	
	public DishType(){}
	
	public DishType(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
    @Override
    public String toString() {
    	return name;
    }
    
    @Override
    public boolean equals(Object o) {
    	if(o == this)
    		return true;
    	if(!(o instanceof DishType))
    		return false;
    	return id.equals(((DishType)o).getId());
    }
}
