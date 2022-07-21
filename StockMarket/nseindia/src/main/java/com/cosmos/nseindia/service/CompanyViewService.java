package com.cosmos.nseindia.service;

import com.cosmos.nseindia.model.CompanyView;
import com.cosmos.nseindia.pojo.CompanyViews;
import com.cosmos.nseindia.repository.CompanyViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyViewService {
    @Autowired
    private CompanyViewRepository companyViewRepository;
    public CompanyView getCompanyViewById(String companyName) {
        Optional<CompanyView> optionalCompanyView = companyViewRepository.findById(companyName);
        if(optionalCompanyView.isPresent()){
            return optionalCompanyView.get();
        }else{
            CompanyView companyView = new CompanyView();
            companyView.setCompanyName(companyName);
            return companyView;
        }
    }

    public CompanyViews getAllCompanyView() {
        CompanyViews companyViews = new CompanyViews();
        List<CompanyView> companyViewList = companyViewRepository.findAll();
        companyViews.setCompanyViewList(companyViewList);
        return companyViews;
    }

    public String updateCompanyView(CompanyView companyView) {
        String str ="";
        try{
            companyViewRepository.save(companyView);
            str = "Success";
        }catch (Exception ex){
            str = "Failed";
        }
        return str;
    }
}
