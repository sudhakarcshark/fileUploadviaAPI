package com.equi.drsforequi.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ThirdPartyService {

    @Value("${thirdparty.api.url}")
    private String thirdPartyApiUrl;

    public String uploadFile(MultipartFile file, String templateId, String memberNumber, String clientId,
                             String updatePlatform, String accountType, String reportType) throws IOException {

        HttpPost post = new HttpPost(thirdPartyApiUrl);

        MultipartEntityBuilder builder = new MultipartEntityBuilder();

        builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
        builder.addTextBody("templateId", templateId, ContentType.TEXT_PLAIN);
        builder.addTextBody("memberNumber", memberNumber, ContentType.TEXT_PLAIN);
        builder.addTextBody("clientId", clientId, ContentType.TEXT_PLAIN);
        builder.addTextBody("updatePlatform", updatePlatform, ContentType.TEXT_PLAIN);
        builder.addTextBody("accountType", accountType, ContentType.TEXT_PLAIN);
        builder.addTextBody("reportType", reportType, ContentType.TEXT_PLAIN);

        HttpEntity entity = builder.build();
        post.setEntity(entity);

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(post)) {

            return EntityUtils.toString(response.getEntity());
        }
    }
}
