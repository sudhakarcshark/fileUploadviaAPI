package com.equi.drsforequi;

import com.equi.drsforequi.controller.FileUploadController;
import com.equi.drsforequi.service.ThirdPartyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileUploadController.class)
@AutoConfigureMockMvc
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ThirdPartyService thirdPartyService;

    @Test
    public void testUploadFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file","test.csv","text/csv", "test data".getBytes());
        Mockito.when(thirdPartyService.uploadFile(
                        Mockito.any(), Mockito.anyString(), Mockito.anyString(),
                        Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn("{\"message\":\"success\",\"fileId\":\"dummyFileId\"}");

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/file")
                        .file(file)
                        .param("templateId", "123")
                        .param("memberNumber", "456")
                        .param("clientId", "789")
                        .param("updatePlatform", "onprem")
                        .param("accountType", "mfi")
                        .param("reportType", "summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.fileId").value("dummyFileId"));
    }

}
