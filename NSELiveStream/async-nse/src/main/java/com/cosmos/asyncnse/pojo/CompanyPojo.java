package com.cosmos.asyncnse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "company001")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyPojo {
    @Id
    private String companyId;
    private String companyName;
    public String status;
}
