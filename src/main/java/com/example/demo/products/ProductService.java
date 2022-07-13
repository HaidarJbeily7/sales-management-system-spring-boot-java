package com.example.demo.products;

import com.example.demo.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    final private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addNewProduct(Product p){
        Product product = productRepository.save(p);
        product.setCreation_date();
        return  product;
    }

    public Product updateProduct(Product p, Category cat, Long id){
        Product product = productRepository.findById(id).get();
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        product.setCategory(cat);
        productRepository.save(product);
        return product;
    }

    public boolean checkIfExists(Long id){
        return  productRepository.findById(id).isPresent();
    }



}
