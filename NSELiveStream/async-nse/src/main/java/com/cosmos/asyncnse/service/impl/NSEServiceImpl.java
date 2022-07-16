package com.cosmos.asyncnse.service.impl;

import com.cosmos.asyncnse.pojo.company.CompanyModel;
import com.cosmos.asyncnse.pojo.nifty.Nifty50;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Service
@Slf4j
public class NSEServiceImpl {
    @Value("${nse.cookie}")
    private String nseCookie;

    @Autowired
    private WebClient.Builder webClientBuilder;
    private  WebClient webClient;

    private String baseUrl = "https://www.nseindia.com";
    private String nseUrlForQuoteEquity = "/api/quote-equity?symbol=";
    private String nseUrlForQuoteEquityLive = baseUrl + "/api/quote-equity?symbol=";

    private String nseUrlForNifty50Live = "/api/equity-stockIndices?index=NIFTY%2050";
    @PostConstruct
    public void init(){
        log.info("Inside bean init method");
        webClient = webClientBuilder.baseUrl(baseUrl)
                .defaultCookie("cookie", nseCookie)
                .defaultHeader("accept-language", "n-US,en;q=0.9")
                .defaultHeader("accept-encoding", "zip,")
                .defaultHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36")
        .build();

    }

    public Mono<CompanyModel> readNSEURL(String companyName){
        String url = nseUrlForQuoteEquity+companyName;

        Mono<CompanyModel> companyModelMono = webClient.get()
                .uri(nseUrlForQuoteEquity+companyName)
                .retrieve()
                .bodyToMono(CompanyModel.class);
        SaveStaticDate(companyModelMono);
        return companyModelMono;
    }

    private void SaveStaticDate(Mono<CompanyModel> companyModelMono) {

    }
}
