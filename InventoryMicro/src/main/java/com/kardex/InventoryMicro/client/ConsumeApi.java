package com.kardex.InventoryMicro.client;

import com.kardex.InventoryMicro.dto.errors.SystemException;
import com.kardex.InventoryMicro.utils.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumeApi {
    @Value("http://localhost:8080/apiRest/product/{productId}")
    private String urlMockApi;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    public ProductDto consumeMockApi(String productId) {
        String apiUrl = urlMockApi.replace("{productId}", productId);

        try {
            ResponseEntity<ProductDto> responseEntity = restTemplate.getForEntity(apiUrl, ProductDto.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                throw new HttpClientErrorException(responseEntity.getStatusCode());
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw SystemException.builder()
                        .code(Constans.LOG_ERROR_002)
                        .description("Product "+Constans.LOG_ERROR_002_DESCRIPTION)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .tecError(Constans.VALIDATION_ERROR)
                        .build();
            } else {
                throw ex;
            }
        }
    }

}
