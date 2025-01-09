package com.equi.drsforequi.controller;

import com.equi.drsforequi.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private final ThirdPartyService thirdPartyService;

    @Autowired
    public FileUploadController(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("templateId") String templateId,
            @RequestParam("memberNumber") String memberNumber,
            @RequestParam("clientId") String clientId,
            @RequestParam("updatePlatform") String updatePlatform,
            @RequestParam("accountType") String accountType,
            @RequestParam("reportType") String reportType) {

        try {
            String response = thirdPartyService.uploadFile(file, templateId, memberNumber, clientId, updatePlatform, accountType, reportType);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
