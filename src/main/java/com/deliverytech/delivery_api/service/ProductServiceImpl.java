package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ProductDTO;
import com.deliverytech.delivery_api.repository.ProductRepository;
import com.deliverytech.delivery_api.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    public ProductRepository repository;

    @Override
    public Long createProduct(ProductDTO productDTO) {
        var modelMapper = new ModelMapper();
        var product = modelMapper.map(productDTO, Product.class);
        var savedProduct = repository.save(product);
        return savedProduct.getId();
    }

    @Override
    public List<ProductDTO> findProductbyRestaurant(Long restaurantID) {
        return List.of();
    }

    @Override
    public ProductDTO findProductById(Long id) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        var product = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Product not found ID: %d".formatted(id)));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setAvailable(product.isAvailable());
        repository.save(product);
        return productDTO;
    }

    @Override
    public ProductDTO updateAvailabilityProduct(Long id, boolean avaliable) {
        return null;
    }

    @Override
    public List<ProductDTO> findProductByCategory(String category) {
        ModelMapper modelMapper = new ModelMapper();
        return repository.findProductByCategory(category)
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(this::ConvertEntityToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO ConvertEntityToDTO(Product entity) {
        var productDTO = new ProductDTO();
        productDTO.setName(entity.getName());
        productDTO.setDescription(entity.getDescription());
        productDTO.setCategory(entity.getCategory());
        productDTO.setPrice(entity.getPrice());
        productDTO.setAvailable(entity.isAvailable());
        return productDTO;
    }
}