package com.orders.ordermanagerdomain;

import java.util.Collections;
import java.util.Comparator;

public class OrderComparator {
	
	public static Comparator<Order> getComparator(String field) {
		if(field.equals("date")) {
			return new DateComparator();
		} else {
			return new IdComparator();
		}
	}
	
	public static Comparator<Order> getReverseComparator(String field) {
		if(field.equals("date")) {
			return Collections.reverseOrder(new DateComparator());
		} else {
			return Collections.reverseOrder(new IdComparator());
		}
	}

}
