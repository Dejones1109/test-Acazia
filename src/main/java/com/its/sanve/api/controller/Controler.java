package com.its.sanve.api.controller;

import com.its.sanve.api.entities.Category;
import com.its.sanve.api.entities.Product;
import com.its.sanve.api.repositories.CategoryRepository;
import com.its.sanve.api.repositories.ProductRepository;
import com.its.sanve.api.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api")
public class Controler {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService service;

    @GetMapping("showCategory")
    public List<Category> showCategory(){
        return categoryRepository.findAll();
    }
    @GetMapping("showProduct")
    public List<Product> showProduct(){
        return productRepository.findAll();
    }
    @PostMapping("editCategory")
    public Object editCategory(@RequestParam Long  id,@RequestParam String name,@RequestParam String tag){
        Category category = categoryRepository.getCategory(id);
        if(category!=null){
            category.setName(name);
            category.setTag(tag);
            categoryRepository.save(category);
            return category;
        }else {
            return "category don't exist";
        }
    }
    @PostMapping("editProduct")
    public Object editProduct(@RequestParam Long  id,@RequestParam String name,@RequestParam String categoryTag,@RequestParam Double price){
        Product product = productRepository.getProduct1(id);
        if(product!=null){
            product.setName(name);
            product.setCategoryTag(categoryTag);
            product.setPrice(price);
            productRepository.save(product);
            return product;
        }else {
            return "category don't exist";
        }
    }
    @PostMapping("createCategory")
    public Object createCategory(@RequestParam String name,@RequestParam String tag){
        if(tag!=categoryRepository.tag(tag)){
            Category category = new Category();
            category.setName(name);
            category.setTag(tag);
            categoryRepository.save(category);
            return category;
        }else {
            return "tag exist!!";
        }

    }
    @PostMapping("createProduct")
    public Object createProduct(@RequestParam String name,@RequestParam String categoryTag,@RequestParam Double price){
        if(categoryTag==categoryRepository.tag(categoryTag)){
            Product product = new Product();
            product.setName(name);
            product.setCategoryTag(categoryTag);
            product.setPrice(price);
            productRepository.save(product);
            return product;
        }else {
            return "tag don't exist in category table!!";
        }

    }
    @PostMapping("deleteCategory")
    public Object   deleteCategory(@RequestParam Long id){
        if(categoryRepository.getCategory(id)!=null){
            categoryRepository.deleteById(id);
            return "delete success";
        }else {
            return "category don't exist";
        }

    }
    @PostMapping("deleteProduct")
    public Object   deleteProduct(@RequestParam Long id){
        if(productRepository.getProduct1(id)!=null){
            productRepository.deleteById(id);
            return "delete success";
        }else {
            return "product don't exist";
        }

    }

    @PostMapping("computing")
    public List<Product> computing(){
        return service.computingByPriceAndName(productRepository.findAll());
    }
}
