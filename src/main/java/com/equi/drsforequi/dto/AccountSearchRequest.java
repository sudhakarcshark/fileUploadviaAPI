package com.equi.drsforequi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSearchRequest {
    private MultipartFile file;
    private String accountNumbers; // Comma-separated
    private String platform; // "onprem"
    private String memberNumber;
    private String clientId;
    private String accountType; // "mfi" or "retail"
    private Integer pageNumber;
    private Integer pageSize;
}
