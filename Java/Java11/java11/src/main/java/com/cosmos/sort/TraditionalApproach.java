package com.cosmos.sort;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TraditionalApproach {
    private static List<Employee> getEmployeesList(){
        List<Employee> employeeList = Stream.of(
                new Employee(100L,"Abhijit","abhijit@gmail.com","Bangalore", LocalDate.of(2021,11,15),126000L)
                ,Employee.builder()
                        .empId(101L)
                        .empName("Aakash")
                        .emailId("aakash@gmail.com")
                        .empSal(154000L)
                        .jdate(LocalDate.of(2021,5,23))
                        .location("Delhi")
                        .build()
                ,Employee.builder()
                        .empId(102L)
                        .empName("Anirudh")
                        .emailId("Anirudh@gmail.com")
                        .empSal(101000L)
                        .jdate(LocalDate.of(2020,8,4))
                        .location("Delhi")
                        .build(),
                Employee.builder()
                        .empId(103L)
                        .empName("Ashish")
                        .emailId("Ashish@gmail.com")
                        .empSal(145000L)
                        .jdate(LocalDate.of(2021,10,17))
                        .location("Hydrabad")
                        .build())
                .collect(Collectors.toList());
    return employeeList;
    }
    private static List<Employee> getEmployeesListSortedByLocation(){
        List<Employee> employeeList = getEmployeesList();
        Collections.sort(employeeList,new LocationComparator());
        return employeeList;
    }
    private static List<Employee> getEmployeesListSortedBySalary(){
        List<Employee> employeeList = getEmployeesList();
        Collections.sort(employeeList,new SalaryComparator());
        return employeeList;
    }
    public static void main(String[] args) {
        List<Employee> employeeList = getEmployeesListSortedBySalary();
        employeeList.stream()
                .forEach(System.out::println);
    }
}
