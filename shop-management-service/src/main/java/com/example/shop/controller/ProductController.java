package com.example.shop.controller;

import com.example.shop.model.Product;
import com.example.shop.repository.ProductRepository;
import com.example.shop.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    // 1. Add a product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = repository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // 2. View all products
    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // 3. View product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " does not exist."));
    }

    // 4. Update a product by ID (Your exact corrected code)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        // Step 1: Check if the product exists in the database
        if (repository.existsById(id)) {
            // Step 2: Set the incoming ID to make sure it updates the correct record
            updatedProduct.setId(id);
            // Step 3: Save the updated product back to the database
            return repository.save(updatedProduct);
        } else {
            // Step 4: If it doesn't exist, throw the custom JSON error exception
            throw new ProductNotFoundException("Cannot update. Product with ID " + id + " does not exist.");
        }
    }

    // 5. Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Cannot delete. Product with ID " + id + " does not exist.");
        }
        repository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product deleted successfully.");
        return ResponseEntity.ok(response);
    }

    // 6. Search products by keyword
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("keyword") String keyword) {
        return repository.searchProducts(keyword);
    }
}
