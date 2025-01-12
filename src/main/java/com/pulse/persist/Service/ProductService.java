package com.pulse.persist.Service;

import com.pulse.persist.DTO.ProductDTO;
import com.pulse.persist.Model.Product;
import com.pulse.persist.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product createProduct(ProductDTO productDTO){

        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());

        return productRepository.save(product);

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
