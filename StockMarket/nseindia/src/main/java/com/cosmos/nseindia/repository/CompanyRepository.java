package com.cosmos.nseindia.repository;

import com.cosmos.nseindia.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,String> {
}
