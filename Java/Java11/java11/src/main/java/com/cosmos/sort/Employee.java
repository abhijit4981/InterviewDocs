package com.cosmos.sort;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employee {
    private Long empId;
    private String empName;
    private String emailId;
    private String location;
    private LocalDate jdate;
    private Long empSal;
}
