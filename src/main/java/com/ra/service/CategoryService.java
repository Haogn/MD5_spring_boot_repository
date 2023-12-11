package com.ra.service;

import com.ra.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryService {
    List<Category> findAll() ;
    Category saveOrUpdate(Category category) ;
    Category findById(Integer id) ;
    void delete(Integer id) ;

}
