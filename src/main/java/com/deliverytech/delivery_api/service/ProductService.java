package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    Long createProduct(ProductDTO productDTO);
    List<ProductDTO> findProductbyRestaurant(Long restaurantID);
    ProductDTO findProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    ProductDTO updateAvailabilityProduct(Long id, boolean avaliable);
    ProductDTO findProductByCategory(String category);
    List<ProductDTO> getAllProducts();
}