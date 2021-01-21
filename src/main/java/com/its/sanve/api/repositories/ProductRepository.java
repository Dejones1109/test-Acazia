package com.its.sanve.api.repositories;

import com.its.sanve.api.entities.Category;
import com.its.sanve.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select c from Product c where c.price=:price")
    Product[] getProduct(@Param("price") Double price);

    @Query(value = "select c from Product c where lower(c.name) =:name")
    Product product(@Param("name") String name);

    @Query(value = "select c from Product c where c.id =:id")
    Product getProduct1(@Param("id") Long id);
}