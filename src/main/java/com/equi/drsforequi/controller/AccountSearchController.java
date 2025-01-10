package com.equi.drsforequi.controller;

import com.equi.drsforequi.dto.AccountSearchRequest;
import com.equi.drsforequi.service.AccountSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/account-search")
public class AccountSearchController {

    @Autowired
    private AccountSearchService accountSearchService;

    @PostMapping("/search")
    public ResponseEntity<?> searchAccounts(
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) String accountNumbers,
            @RequestParam String platform,
            @RequestParam String memberNumber,
            @RequestParam String clientId,
            @RequestParam String accountType,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {

        AccountSearchRequest request = new AccountSearchRequest();
        request.setFile(file);
        request.setAccountNumbers(accountNumbers);
        request.setPlatform(platform);
        request.setMemberNumber(memberNumber);
        request.setClientId(clientId);
        request.setAccountType(accountType);
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);

        return accountSearchService.searchAccounts(request);
    }
}
