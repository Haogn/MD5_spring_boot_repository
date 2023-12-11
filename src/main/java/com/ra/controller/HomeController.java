package com.ra.controller;

import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService ;

    @Autowired
    private ProductService productService ;
    @GetMapping("")
    public String home(Model model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("list", list) ;
        return "home" ;
    }

    @GetMapping("/add-category")
    public String createCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category) ;
        return "add-category" ;
    }

    @PostMapping("/add-category")
    public String postCreateCategory(@ModelAttribute("category") Category category) {
        if (categoryService.saveOrUpdate(category) != null) {
            return "redirect:/" ;
        }
       return "add-category" ;
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return "redirect:" ;
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.findById(id) ;
        model.addAttribute("category" , category) ;
        return "edit-category" ;
    }

    @PostMapping("/update-category")
    public String postEditCategory(@ModelAttribute("category") Category category) {
       if ( categoryService.saveOrUpdate(category) != null) {
           return "redirect:/" ;
       }
       return "edit-category" ;
    }


//    product
    @GetMapping("/list-product")
    public String listProduct(Model model) {
        List<Product> list = productService.findAll() ;
        List<Category> listCate = categoryService.findAll() ;
         model.addAttribute("listPro" , list) ;
         model.addAttribute("listCate" , listCate) ;
        return "list-product" ;
    }

    @GetMapping("add-product")
    public String createProduct(Model model) {
        Product product = new Product() ;
        List<Category> list = categoryService.findAll();
        model.addAttribute("list", list) ;
        model.addAttribute("product", product) ;
        return "add-product" ;
    }

    @PostMapping("/add-product")
    public String postCreateProduct(@ModelAttribute("product") Product product) {
        if (productService.saveOrUpdate(product) != null) {
            return "redirect:/list-product" ;
        }
        return "add-product" ;
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "redirect:/list-product" ;
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Integer id , Model model) {
        Product product = productService.findById(id) ;
        List<Category> list = categoryService.findAll();
        model.addAttribute("list", list) ;
        model.addAttribute("product", product) ;
        return "edit-product" ;
    }

    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute("product")Product product) {
        if (productService.saveOrUpdate(product) != null) {
            return "redirect:/list-product" ;
        }
        return "edit-product";
    }

}
