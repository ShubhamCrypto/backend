package dev.shubham.productcatalog.Services;

import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImple")
public class SelfProductServiceImple implements ProductService{
    @Override
    public GenricProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenricProductDto createProduct(GenricProductDto genricProductDto) {
        return null;
    }

    @Override
    public List<GenricProductDto> getProducts() {

        return null;
    }

    @Override
    public GenricProductDto deleteProduct(Long id) {
        return null;
    }



    @Override
    public GenricProductDto updateProductById(Long id, UpdateProductRequestDto updateProductRequestDto) {
        return null;
    }


}
