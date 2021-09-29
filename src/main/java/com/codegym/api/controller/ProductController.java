package com.codegym.api.controller;

import com.codegym.api.models.Product;
import com.codegym.api.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product/api")
public class ProductController {
    @Autowired
    IProductService iProductService;

    @GetMapping
    public ArrayList<Product> products() {
        return (ArrayList<Product>) iProductService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable int id) {
        Optional<Product> optionalProduct = iProductService.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(iProductService.save(product), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestBody Product product) {
        Optional<Product> optionalProduct = iProductService.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        product.setId(optionalProduct.get().getId());
        return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        Optional<Product> optionalProduct = iProductService.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        iProductService.remove(id);
        return new ResponseEntity<>(optionalProduct.get(), HttpStatus.NO_CONTENT);
    }

}
