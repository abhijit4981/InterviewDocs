package com.cosmos.nseindia.controller;

import com.cosmos.nseindia.model.Company;
import com.cosmos.nseindia.pojo.Companies;
import com.cosmos.nseindia.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nse")
public class NSEController {
    @Autowired
    private CompanyService companyService;
    @PostMapping("/save/{companyName}")
    public Company saveCompany(@PathVariable String companyName){
        return companyService.saveCompany(companyName);
    }
    @GetMapping
    public Companies getAllCompanies(){
        return companyService.getAllCompanies();
    }
}
