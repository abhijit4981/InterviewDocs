package com.cosmos.nseindia.service;

import com.cosmos.nseindia.model.Company;
import com.cosmos.nseindia.pojo.Companies;
import com.cosmos.nseindia.pojo.company.CompanyModel;
import com.cosmos.nseindia.repository.CompanyRepository;
import com.cosmos.nseindia.util.NseUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NseUtility nseUtility;

    @Value("${nse.cookie}")
    private String nseCookie;
    private String baseUrl = "https://www.nseindia.com";
    private String nseUrlForQuoteEquity01 = baseUrl + "/api/quote-equity?symbol=";

    public Company saveCompany(String companyName) {
        ResponseEntity<String> responseEntity = null;
        try{
            responseEntity = makeTheRestCall(companyName);
        } catch (Exception ex){
            log.info("Try again");
            responseEntity = makeTheRestCall(companyName);
        }
        CompanyModel companyModel = createCompanyModelobject(responseEntity);
        Company company = null;
        company = createCompanyObject(companyModel);
        log.info("this is the response : "+company);

        Company company1 = new Company();
        company1 = saveCompanyToDB(company);

        return company1;
    }

    private Company saveCompanyToDB(Company company) {
        Company company1 = new Company();
        try{
            company1 = companyRepository.save(company);
        }catch (Exception ex){
            System.out.println("Company could not saved successfully :"+company);
        }
        System.out.println("Company saved successfully :"+company1);
        return company1;
    }

    private CompanyModel createCompanyModelobject(ResponseEntity<String> responseEntity) {
        ObjectMapper mapper = new ObjectMapper();

        CompanyModel companyModel = null;
        try {
            companyModel = mapper.readValue(responseEntity.getBody(), CompanyModel.class);
        } catch (JsonProcessingException e) {
            log.error("Some error parsing"+e.getMessage());
            throw new RuntimeException("Some error parsing Company Object with JSON "+e.getMessage());
        }
        return companyModel;
    }

    private ResponseEntity<String> makeTheRestCall(String companyName) {
        //log.info("cookie val is : "+nseCookie);
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "n-US,en;q=0.9");
        headers.set("accept-encoding", "zip,");
        headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        headers.set("cookie", nseCookie);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        String url = nseUrlForQuoteEquity01+companyName;
        //HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        log.info("Going to call url : "+url);
        ResponseEntity<String> responseForQuoteEquity01 = restTemplate.exchange(url , HttpMethod.GET, entity , String.class);
        return  responseForQuoteEquity01;
    }

    private Company createCompanyObject(CompanyModel companyModel) {
        Company nseListedCompanyModel = new Company();
        nseListedCompanyModel.setCompanyName(companyModel.info.symbol);
        nseListedCompanyModel.setIsin(companyModel.getInfo().getIsin());
        nseListedCompanyModel.setStatus(companyModel.getMetadata().getStatus());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate listingDate = LocalDate.parse(companyModel.getMetadata().getListingDate(),formatter);
        nseListedCompanyModel.setListingDate(listingDate);
        nseListedCompanyModel.setIndustryInfo(companyModel.getIndustryInfo());
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss");
        LocalDate lastUpdateTime = LocalDate.parse(companyModel.getPreOpenMarket().getLastUpdateTime(),formatter1);
        nseListedCompanyModel.setLastUpdateTime(lastUpdateTime);
        nseListedCompanyModel.setIntraDayHighLow(companyModel.getPriceInfo().getIntraDayHighLow());
        //nseListedCompanyModel.setWeekHighLow(companyModel.getPriceInfo().getWeekHighLow());
        nseListedCompanyModel.setMinOfWeek(companyModel.getPriceInfo().getWeekHighLow().getMin());
        LocalDate minWeekDate = LocalDate.parse(companyModel.getPriceInfo().getWeekHighLow().getMinDate(),formatter);
        nseListedCompanyModel.setMinWeekDate(minWeekDate);
        nseListedCompanyModel.setMaxOfWeek(companyModel.getPriceInfo().getWeekHighLow().getMax());
        LocalDate maxWeekDate = LocalDate.parse(companyModel.getPriceInfo().getWeekHighLow().getMaxDate(),formatter);
        nseListedCompanyModel.setMaxWeekDate(maxWeekDate);
        return nseListedCompanyModel;
    }

    public Companies getAllCompanies() {
        List<Company> companyList = companyRepository.findAll();
        Companies companies = new Companies();
        companies.setCompanyList(companyList);
        return companies;
    }
    public void saveCompaniesAsCompletableFuture(){
        NseUtility.nseListedCompany.stream()
                .map(companyName-> CompletableFuture.supplyAsync(()->makeTheRestCall(companyName))
                        .orTimeout(1, TimeUnit.SECONDS)
                        .thenApply(responseEntity->createCompanyModelobject(responseEntity))
                        .thenApply(companyModel->createCompanyObject(companyModel))
                        .thenApply(company -> saveCompanyToDB(company)))
                        .forEach(System.out::println);
    }
}
