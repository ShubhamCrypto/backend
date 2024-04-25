package dev.shubham.productcatalog.Services;

import dev.shubham.productcatalog.dtos.FakeStoreProductDto;
import dev.shubham.productcatalog.dtos.GenricProductDto;

import java.util.List;

public interface ProductService {
    GenricProductDto getProductById(Long id);


    GenricProductDto createProduct(GenricProductDto genricProductDto);

    List<GenricProductDto> getProducts();
    GenricProductDto deleteProduct(Long id);
}
