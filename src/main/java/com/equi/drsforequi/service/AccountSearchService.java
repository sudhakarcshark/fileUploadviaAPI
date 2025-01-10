package com.equi.drsforequi.service;

import com.equi.drsforequi.config.RestTempletConfig;
import com.equi.drsforequi.dto.AccountSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountSearchService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${Dctrlr.api.url}")
    private String DctrlApiUrl;

    public ResponseEntity<?> searchAccounts(AccountSearchRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        Map<String, Object> body = new HashMap<>();
        if (request.getFile() != null) {
            body.put("file", request.getFile());
        }
        if (request.getAccountNumbers() != null) {
            body.put("accountNumbers", request.getAccountNumbers());
        }
        body.put("platform", request.getPlatform());
        body.put("memberNumber", request.getMemberNumber());
        body.put("clientId", request.getClientId());
        body.put("accountType", request.getAccountType());
        body.put("pageNumber", request.getPageNumber());
        body.put("pageSize", request.getPageSize());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(DctrlApiUrl, HttpMethod.POST,entity, String.class);

    }
}
