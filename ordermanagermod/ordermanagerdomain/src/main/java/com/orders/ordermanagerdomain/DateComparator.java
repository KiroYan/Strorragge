package com.orders.ordermanagerdomain;

import java.util.Comparator;

public class DateComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getDate().compareTo(o2.getDate());
	}

}
