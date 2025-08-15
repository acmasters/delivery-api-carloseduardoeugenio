package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.ProductDTO;
import com.deliverytech.delivery_api.repository.ProductRepository;
import com.deliverytech.delivery_api.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    public ProductRepository repository;

    @Override
    public Long createProduct(ProductDTO productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = repository.save(product);
        return savedProduct.getId();
    }

    @Override
    public List<ProductDTO> findProductbyRestaurant(Long Idrestaurant) {
        return List.of();
    }

    @Override
    public ProductDTO findProductById(Long id) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));
        ModelMapper modelMapper = new ModelMapper();
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
    public ProductDTO findProductByCategory(String category) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return List.of();
    }
}
