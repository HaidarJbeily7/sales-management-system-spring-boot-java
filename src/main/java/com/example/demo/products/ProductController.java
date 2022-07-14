package com.example.demo.products;

import com.example.demo.categories.Category;
import com.example.demo.categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    final private ProductService productService;
    final private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public List<Product> getProducts(@PathVariable int pageNo,
                                     @PathVariable int pageSize) {
        return productService.getProducts(pageNo, pageSize);
    }

    @PostMapping
    public Product addProduct(@Valid @RequestBody  Product p){
        if(!categoryService.checkIfExists(p.getCategory().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category is invalid");
        Product product = productService.addNewProduct(p);
        Long cat_id = product.getCategory().getId();
        Category category = categoryService.getCategoryById(cat_id);
        product.setCategory(category);
        return  product;
    }

    @PutMapping("/{id}")
    public Product editProduct(@Valid @RequestBody Product p, @PathVariable("id") Long id){
        Long cat_id = p.getCategory().getId();
        if(!productService.checkIfExists(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if(!categoryService.checkIfExists(cat_id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category is invalid");
        Category cat = categoryService.getCategoryById(cat_id);
        Product product = productService.updateProduct(p, cat, id);
        return product;
    }
}
