package com.cosmos.sort;

import java.util.Comparator;

public class LocationComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return -(o1.getLocation().compareTo(o2.getLocation()));
    }
}
