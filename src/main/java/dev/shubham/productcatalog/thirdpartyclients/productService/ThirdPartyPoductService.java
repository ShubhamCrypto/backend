package dev.shubham.productcatalog.thirdpartyclients.productService;

import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import dev.shubham.productcatalog.exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyPoductService {
    GenricProductDto getProductById(Long id) throws NotFoundException;

    GenricProductDto createProduct(GenricProductDto genricProductDto);

    List<GenricProductDto> getProducts();
    GenricProductDto deleteProduct(Long id);

    GenricProductDto updateProductById(Long id, UpdateProductRequestDto updateProductRequestDto);
}

//this package says that class which are CLIENT of fakeStore API are writtern here.