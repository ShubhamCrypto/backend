package dev.shubham.productcatalog.Services;

import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import dev.shubham.productcatalog.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenricProductDto getProductById(Long id) throws NotFoundException;


    GenricProductDto createProduct(GenricProductDto genricProductDto);

    List<GenricProductDto> getProducts();
    GenricProductDto deleteProduct(Long id);

    GenricProductDto updateProductById(Long id, UpdateProductRequestDto updateProductRequestDto);
}
