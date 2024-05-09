package dev.shubham.productcatalog.Services;

import dev.shubham.productcatalog.dtos.FakeStoreProductDto;
import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import dev.shubham.productcatalog.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.List;


@Service("fakeStoreProxyProductService")
public class FakeStoreProxyProductService implements ProductService{
    RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestUrl ="https://fakestoreapi.com/products/{id}";
    private String productsRequestBaseUrl ="https://fakestoreapi.com/products";
    public FakeStoreProxyProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public GenricProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity= restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("product with id : "+id+ " does not exist.");
        }
        GenricProductDto product=convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductDto);
        return product;

    }

    @Override
    public GenricProductDto createProduct(GenricProductDto genricProductDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<GenricProductDto> responseEntity=restTemplate.postForEntity(productsRequestBaseUrl,genricProductDto, GenricProductDto.class);
        return responseEntity.getBody();
    }

//    @Override
//    public List<GenricProductDto> getProducts() {
//        RestTemplate restTemplate=restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto[]> responseEntity=restTemplate.getForEntity(productsRequestBaseUrl,FakeStoreProductDto[].class);
//        List<GenricProductDto> ans=new ArrayList<>();
//        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(responseEntity.getBody()).toList()){
//            GenricProductDto product=new GenricProductDto();
//            product.setImage(fakeStoreProductDto.getImage());
//            product.setDescription(fakeStoreProductDto.getDescription());
//            product.setPrice(fakeStoreProductDto.getPrice());
//            product.setTitle(fakeStoreProductDto.getTitle());
//            product.setCategory(fakeStoreProductDto.getCategory());
//            ans.add(product);
//        }
//        return ans;
//    }

    @Override
    public List<GenricProductDto> getProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductDto>> responseEntity=restTemplate.exchange(
                productsRequestBaseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
        });
        List<GenricProductDto> ans=new ArrayList<>();
        List<FakeStoreProductDto> response=responseEntity.getBody();
        for(FakeStoreProductDto fakeStoreProductDto:response){
            GenricProductDto product=convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductDto);
            ans.add(product);
        }
        return ans;
    }

    @Override
    public GenricProductDto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.execute(specificProductRequestUrl,HttpMethod.DELETE,requestCallback,responseExtractor,id);
        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();
        return convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductDto);


    // we have to return like we did in case of get for enitity
        // getForEntity=> exchnage=> execute
//        public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
//            RequestCallback requestCallback = this.acceptHeaderRequestCallback(responseType);
//            ResponseExtractor<ResponseEntity<T>> responseExtractor = this.responseEntityExtractor(responseType);
//            return (ResponseEntity)nonNull((ResponseEntity)this.execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables));
//        }

    }

    @Override
    public GenricProductDto updateProductById(Long id, UpdateProductRequestDto updateProductRequestDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity=restTemplate.getForEntity(specificProductRequestUrl,FakeStoreProductDto.class,id);
        System.out.println(responseEntity.getBody().getId());
        System.out.println("**********");
        FakeStoreProductDto fakeStoreProductDto=responseEntity.getBody();
        fakeStoreProductDto.setCategory(updateProductRequestDto.getCategory());
        fakeStoreProductDto.setTitle(updateProductRequestDto.getTitle());
        fakeStoreProductDto.setPrice(updateProductRequestDto.getPrice());
        fakeStoreProductDto.setImage(updateProductRequestDto.getImage());
        fakeStoreProductDto.setDescription(updateProductRequestDto.getDescription());
        GenricProductDto genricProductDto=convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductDto);
        System.out.println(genricProductDto.getId());
        ResponseEntity<GenricProductDto> result=restTemplate.postForEntity(productsRequestBaseUrl,genricProductDto,GenricProductDto.class);
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

