package com.its.sanve.api.repositories;

import com.its.sanve.api.entities.Category;
import com.its.sanve.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,Long> {
    @Query(value = "select c from Category c where c.id=:id")
    Category getCategory(@Param("id") Long id);
    @Query(value = "select c.tag from Category c where c.tag=:tag")
    String tag(@Param("tag") String tag);
}
