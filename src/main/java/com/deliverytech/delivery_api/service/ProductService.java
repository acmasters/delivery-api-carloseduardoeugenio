package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public Long createProduct(ProductDTO productDTO);
    public List<ProductDTO> findProductbyRestaurant(Long restaurantID);
    public ProductDTO findProductById(Long id);
    public ProductDTO updateProduct(Long id, ProductDTO productDTO);
    public ProductDTO updateAvailabilityProduct(Long id, boolean avaliable);
    public ProductDTO findProductByCategory(String category);
    public List<ProductDTO> getAllProducts();
}