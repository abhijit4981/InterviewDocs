package com.cosmos.asyncnse.controller;

import com.cosmos.asyncnse.pojo.company.CompanyModel;
import com.cosmos.asyncnse.pojo.company.PriceInfo;
import com.cosmos.asyncnse.pojo.nifty.Nifty50;
import com.cosmos.asyncnse.service.impl.NSEServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/nse")
@Slf4j
public class NSERestController {
    @Autowired
    private NSEServiceImpl nseService;
    @GetMapping("/{companyName}")
    public Mono<CompanyModel> getCompanyModel(@PathVariable String companyName){
        log.info("Going to Fetch details fo this company : "+companyName);
        return nseService.readNSEURL(companyName);
    }
}
