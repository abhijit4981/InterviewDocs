package com.cosmos.sort;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return -(Long.compare(o1.getEmpSal(),o2.getEmpSal()));
    }
}
