package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ProductDTO;
import com.deliverytech.delivery_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/findall")
    public List<ProductDTO>getAllProducts() {
        return null;
    }
    @PostMapping
    public ResponseEntity<Long> createProduct(
            @Valid
            @RequestBody
            ProductDTO dto) {
        Long ok = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ok);
    }

    @PatchMapping("/{id}/product")
    public ResponseEntity<ProductDTO> updateProduct(Long id, @RequestBody ProductDTO dto) {
        ProductDTO savedProduct = productService.updateProduct(id, dto);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping
    public List<ProductDTO> listProducts(){
        return productService.getAllProducts();
    }
}