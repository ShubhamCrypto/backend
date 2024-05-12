package dev.shubham.productcatalog.Services;

import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import dev.shubham.productcatalog.exceptions.NotFoundException;
import dev.shubham.productcatalog.thirdpartyclients.productService.fakestore.FakeStoreProductDto;
import dev.shubham.productcatalog.thirdpartyclients.productService.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Primary
@Service("fakeStoreProxyProductService")
public class FakeStoreProxyProductService implements ProductService{
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreProxyProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient=fakeStoreProductServiceClient;
    }
    @Override
    public GenricProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenricProductDto createProduct(GenricProductDto genricProductDto) {
       return convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductServiceClient.createProduct(genricProductDto));
    }
    @Override
    public List<GenricProductDto> getProducts() {
        List<GenricProductDto> genricProductDtos=new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductServiceClient.getProducts()){
            genricProductDtos.add(convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductDto));
        }
       return genricProductDtos;
    }

    @Override
    public GenricProductDto deleteProduct(Long id) {

        return convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductServiceClient.deleteProduct(id));
    }

    @Override
    public GenricProductDto updateProductById(Long id, UpdateProductRequestDto updateProductRequestDto) {
       return convertFakeStoreProductDtoIntoGenricProductDto(fakeStoreProductServiceClient.updateProductById(id, updateProductRequestDto));
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

