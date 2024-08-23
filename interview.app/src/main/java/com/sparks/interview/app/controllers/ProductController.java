package com.sparks.interview.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparks.interview.app.exceptions.ProductNotFoundException;
import com.sparks.interview.app.models.Product;
import com.sparks.interview.app.services.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {


    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<Page<Product>> getAllProductsPaginated(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "2") int size){
        Page<Product> listPro = productService.getAllProductsPagination(page, size);
        return new ResponseEntity<>(listPro,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductId(@PathVariable Long productId) throws ProductNotFoundException {
        Product pro=productService.getProductById(productId);
        return new ResponseEntity<>(pro, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insertProduct(@RequestBody Product product){
        Product insertedProduct=productService.addProduct(product);
        log.info("The product is added to db {}",insertedProduct);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<HttpStatus> updateProducts(@RequestBody Product product,@PathVariable Long productId) throws ProductNotFoundException {
        Product updatedProduct=productService.updateProduct(productId,product);
        log.info("The product is updated to db {}",updatedProduct);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/revenue")
    public ResponseEntity<Long> getRevenueOfAllProducts(){
        return new ResponseEntity<>(productService.getTotalRevenue(),HttpStatus.OK);
    }

    @GetMapping("/revenue/{productId}")
    public ResponseEntity<Long> getRevenueOfAllProducts(@PathVariable Long productId) throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getRevenueByProduct(productId),HttpStatus.OK);
    }

}
