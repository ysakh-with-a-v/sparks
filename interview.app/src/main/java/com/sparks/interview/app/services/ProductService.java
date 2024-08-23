package com.sparks.interview.app.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sparks.interview.app.exceptions.ProductNotFoundException;
import com.sparks.interview.app.models.Product;
import com.sparks.interview.app.models.ProductRepository;
import com.sparks.interview.app.models.Sale;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Page<Product> getAllProductsPagination(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

     public Product getProductById(long id) throws ProductNotFoundException {
         return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
     }

     public Product addProduct(Product product){
        return productRepository.save(product);
     }

    public Product updateProduct(long id, Product updatedProduct) throws ProductNotFoundException {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setPrice(updatedProduct.getPrice());
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

    public long getTotalRevenue(){
        AtomicLong revenue= new AtomicLong();
        getAllProducts().forEach(product ->
                revenue.set(revenue.get() +
                        product.getSales().stream().
                                mapToLong(Sale::getQuantity).sum() * product.getPrice()));
        return revenue.get();
    }

    public long getRevenueByProduct(long productId) throws ProductNotFoundException {
        Product product=getProductById(productId);
        return product.getSales().stream().mapToLong(Sale::getQuantity).sum() * product.getPrice();

    }


}
