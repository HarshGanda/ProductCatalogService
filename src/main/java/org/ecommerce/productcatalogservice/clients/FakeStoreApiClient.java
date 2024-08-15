package org.ecommerce.productcatalogservice.clients;

import org.ecommerce.productcatalogservice.dtos.FakeStoreProductRequestDTO;
import org.ecommerce.productcatalogservice.dtos.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.ecommerce.productcatalogservice.constants.Constants.FAKE_STORE_API;

@Component
public class FakeStoreApiClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // GET - fakestoreapi.com/products/id
    public FakeStoreProductResponseDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity(   FAKE_STORE_API + id,
                                            FakeStoreProductResponseDTO.class).getBody();
    }

    // POST - fakestoreapi.com/products
    public FakeStoreProductResponseDTO createProduct(FakeStoreProductRequestDTO fakeStoreProductRequestDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.postForEntity(  FAKE_STORE_API,
                                            fakeStoreProductRequestDTO,
                                            FakeStoreProductResponseDTO.class).getBody();
    }

    // GET - fakestoreapi.com/products
    public FakeStoreProductResponseDTO[] getProducts( ) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity(   FAKE_STORE_API,
                                            FakeStoreProductResponseDTO[].class).getBody();
    }

    // PUT - fakestoreapi.com/products/id
    public FakeStoreProductResponseDTO replaceProduct(Long id, FakeStoreProductRequestDTO input) {
        ResponseEntity<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOResponseEntity =
                requestForEntity(   FAKE_STORE_API + id,
                                    HttpMethod.PUT,
                                    input,
                                    FakeStoreProductResponseDTO.class,
                                    id);
        return fakeStoreProductResponseDTOResponseEntity.getBody();
    }

    // DELETE - fakestoreapi.com/products/id
    public FakeStoreProductResponseDTO deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOResponseEntity =
                requestForEntity(   FAKE_STORE_API + id,
                                    HttpMethod.DELETE,
                                    null,
                                    FakeStoreProductResponseDTO.class,
                                    id);
        return fakeStoreProductResponseDTOResponseEntity.getBody();
    }

    // Generic method to make a request to the FakeStore API
    private <T> ResponseEntity<T> requestForEntity(String url,
                                                   HttpMethod httpMethod,
                                                   @Nullable Object request,
                                                   Class<T> responseType,
                                                   Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

}
