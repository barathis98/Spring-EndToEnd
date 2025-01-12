package com.pulse.persist.Controller;

import com.pulse.persist.DTO.ProductDTO;
import com.pulse.persist.Model.Product;
import com.pulse.persist.Service.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts(){
            return ResponseEntity.ok().body( productService.getAllProducts());
    }
}
