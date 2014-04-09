package com.orders.ordermanagerdomain;

import java.util.Comparator;

public class IdComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getId().compareTo(o2.getId());
	}

}
