package com.cosmos.nseindia.controller;

import com.cosmos.nseindia.model.CompanyView;
import com.cosmos.nseindia.pojo.CompanyViews;
import com.cosmos.nseindia.service.CompanyViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companyview")
public class CompanyViewController {
    @Autowired
    private CompanyViewService companyViewService;

    @GetMapping("/{companyName}")
    public CompanyView getCompanyViewById(@PathVariable String companyName){
        return companyViewService.getCompanyViewById(companyName);
    }
    @GetMapping
    public CompanyViews getAllCompanyView(){
        return companyViewService.getAllCompanyView();
    }
    @PutMapping("/{companyName}")
    public String updateCompanyView(@PathVariable String companyName,@RequestBody CompanyView companyView){
        return companyViewService.updateCompanyView(companyView);
    }
}
