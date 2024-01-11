package com.eemrezcn.channel.controller;

import com.eemrezcn.channel.entity.Product;
import com.eemrezcn.channel.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController
{
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // http://localhost:8080/api/v1/products/search?query=apple
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query){
         return ResponseEntity.ok(productService.searchProducts(query));
    }

    // http://localhost:8080/api/v1/products
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

}
