package dev.shubham.productcatalog.Controllers;

import dev.shubham.productcatalog.Services.ProductService;
import dev.shubham.productcatalog.dtos.GenricProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
//    @Autowired  => field injection
    private ProductService productService;

    //Constructor injection
    public ProductController(@Qualifier("fakeStoreProxyProductService") ProductService productService){
        this.productService=productService;
    }

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService){
//        this.productService=productService;
//    }
    @GetMapping()
        public List<GenricProductDto> getAllProducts(){
        return productService.getProducts();

    }
    @GetMapping("/{id}")
    public GenricProductDto getProductById(@PathVariable("id") Long id){
      return  productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public GenricProductDto deleteProductById(@PathVariable("id") Long id){
        return this.productService.deleteProduct(id);
    }
    @PostMapping
    public GenricProductDto createProduct(@RequestBody GenricProductDto genricProductDto){
        return productService.createProduct(genricProductDto);
    }
    public void updateProductByid(){

    }
}
