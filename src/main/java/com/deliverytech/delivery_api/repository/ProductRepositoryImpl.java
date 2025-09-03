package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findProductByRestaurantId(Long restauranteId) {
        return null;
    }

    @Override
    public List<Product> findByAvailabilityIsTrue() {
        String formattedString = String.format("SELECT * FROM product WHERE isAvailable = true");
        TypedQuery<Product> query =  entityManager.createQuery(formattedString, Product.class);
        return query.getResultList();
    }

    public List<Product> findByCategory(String category) {
        String formattedString = String.format("SELECT * FROM product WHERE category = '%param'", category);
        TypedQuery<Product> query = entityManager.createQuery(formattedString, Product.class);
        return query.getResultList();
    }

    public List<Product> findByPriceLessThanEqual(BigDecimal price) {
        return null;
    }
}