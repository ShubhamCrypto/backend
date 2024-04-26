package dev.shubham.productcatalog.Controllers;

import dev.shubham.productcatalog.Services.ProductService;
import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GenricProductDto> deleteProductById(@PathVariable("id") Long id){
        //return this.productService.deleteProduct(id);
        return new ResponseEntity<GenricProductDto>(
                this.productService.deleteProduct(id),
                HttpStatus.OK
        );
    }
    @PostMapping
    public GenricProductDto createProduct(@RequestBody GenricProductDto genricProductDto){
        return productService.createProduct(genricProductDto);
    }
    @PutMapping("/{id}")
    public GenricProductDto updateProduct(@PathVariable("id")Long id,@RequestBody UpdateProductRequestDto updateProductRequestDto){
        return productService.updateProductById(id,updateProductRequestDto);
    }
}
