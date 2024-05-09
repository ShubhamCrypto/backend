package dev.shubham.productcatalog.thirdpartyclients.productService.fakestore;

import dev.shubham.productcatalog.dtos.FakeStoreProductDto;
import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import dev.shubham.productcatalog.exceptions.NotFoundException;
import dev.shubham.productcatalog.thirdpartyclients.productService.ThirdPartyPoductService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FakeStoreProductServiceClient implements ThirdPartyPoductService {
    RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestUrl ="https://fakestoreapi.com/products/{id}";
    private String productsRequestBaseUrl ="https://fakestoreapi.com/products";
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
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
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        return convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductDto);
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
