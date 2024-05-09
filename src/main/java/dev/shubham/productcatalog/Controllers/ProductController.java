package dev.shubham.productcatalog.Controllers;

import dev.shubham.productcatalog.Services.ProductService;
import dev.shubham.productcatalog.dtos.ExceptionDto;
import dev.shubham.productcatalog.dtos.GenricProductDto;
import dev.shubham.productcatalog.dtos.request.UpdateProductRequestDto;
import dev.shubham.productcatalog.exceptions.NotFoundException;
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
    public GenricProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException{
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
    //v1
    //THIS CODE SHOULD NOT BE IN CONTROLLER
    //ALSO MULTIPLE CONTROLLER CAN THROW NOT FOUND EXCEPTION WE NEED TO REWRITE CDE IN EVERY CONTROLLER

    //SO THIS IS MOVED TO "exceptions/ControllerAdvices"
//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(
//              new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND
//        );
//    }


    // v0
    // i dont get the coreect http status here if i return void
//    @ExceptionHandler(NotFoundException.class)
//    private void handleNotFoundException(){
//        System.out.println("not found exception");
//    }

    @PostMapping
    public GenricProductDto createProduct(@RequestBody GenricProductDto genricProductDto){
        return productService.createProduct(genricProductDto);
    }
    @PutMapping("/{id}")
    public GenricProductDto updateProduct(@PathVariable("id")Long id,@RequestBody UpdateProductRequestDto updateProductRequestDto){
        return productService.updateProductById(id,updateProductRequestDto);
    }
}
