package dev.shubham.productcatalog.thirdpartyclients.productService.fakestore;

import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import dev.shubham.productcatalog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient  {
    RestTemplateBuilder restTemplateBuilder;

    //using appn.properties
//    private String specificProductRequestUrl ="https://fakestoreapi.com/products/{id}";
//    private String productsRequestBaseUrl ="https://fakestoreapi.com/products";

    private String specificProductRequestUrl ;
    private String productsRequestBaseUrl ;
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakestoreApiUrl,
                                         @Value("${fakestore.api.path.product}")String fakestoreProductApiPath){
        this.restTemplateBuilder=restTemplateBuilder;
        this.productsRequestBaseUrl =fakestoreApiUrl+fakestoreProductApiPath;
        this.specificProductRequestUrl=fakestoreApiUrl+fakestoreProductApiPath+"/{id}";
    }
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("product with id : "+id+ " does not exist.");
        }

        return fakeStoreProductDto;

    }


    public FakeStoreProductDto createProduct(GenricProductDto product) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=
                restTemplate.postForEntity(productsRequestBaseUrl,product, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }


    public List<FakeStoreProductDto> getProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity=restTemplate.getForEntity(productsRequestBaseUrl,FakeStoreProductDto[].class);
        return Arrays.stream(responseEntity.getBody()).toList();
    }

//    @Override
//    public List<GenricProductDto> getProducts() {
//        RestTemplate restTemplate=restTemplateBuilder.build();
//        ResponseEntity<List<FakeStoreProductDto>> responseEntity=restTemplate.exchange(
//                productsRequestBaseUrl,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
//                });
//        List<GenricProductDto> ans=new ArrayList<>();
//        List<FakeStoreProductDto> response=responseEntity.getBody();
//        for(FakeStoreProductDto fakeStoreProductDto:response){
//            GenricProductDto product=convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductDto);
//            ans.add(product);
//        }
//        return ans;
//    }


    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.execute(specificProductRequestUrl,HttpMethod.DELETE,requestCallback,responseExtractor,id);
        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();
        return fakeStoreProductDto;


        // we have to return like we did in case of get for enitity
        // getForEntity=> exchnage=> execute
//        public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
//            RequestCallback requestCallback = this.acceptHeaderRequestCallback(responseType);
//            ResponseExtractor<ResponseEntity<T>> responseExtractor = this.responseEntityExtractor(responseType);
//            return (ResponseEntity)nonNull((ResponseEntity)this.execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables));
//        }

    }


    public FakeStoreProductDto updateProductById(Long id, UpdateProductRequestDto updateProductRequestDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.getForEntity(specificProductRequestUrl,FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();
        fakeStoreProductDto.setCategory(updateProductRequestDto.getCategory());
        fakeStoreProductDto.setTitle(updateProductRequestDto.getTitle());
        fakeStoreProductDto.setPrice(updateProductRequestDto.getPrice());
        fakeStoreProductDto.setImage(updateProductRequestDto.getImage());
        fakeStoreProductDto.setDescription(updateProductRequestDto.getDescription());
        GenricProductDto genricProductDto=convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> result=restTemplate.postForEntity(productsRequestBaseUrl,genricProductDto,FakeStoreProductDto.class);
        return result.getBody();
    }

    private GenricProductDto convertFakeStoreProductDtoIntoGenricProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenricProductDto product=new GenricProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
}
