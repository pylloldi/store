package com.pyc.store.product.repository;

import com.pyc.store.product.entity.Category;
import com.pyc.store.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    public List<Product> findByCategory(Category category);
}
