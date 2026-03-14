package com.klu.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.Product;
import com.klu.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController 
{

    @Autowired
    private ProductService service;

    // check product by id
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id)
    {
        return service.getProductById(id);
    }

    // category search
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category)
    {
        return service.getByCategory(category);
    }

    // price range
    @GetMapping("/filter")
    public List<Product> filterByPrice(@RequestParam double min,@RequestParam double max)
    {

        return service.getByPriceRange(min,max);
    }

    // sorted products
    @GetMapping("/sorted")
    public List<Product> getSortedProducts()
    {
        return service.getSortedProducts();
    }

    // expensive products
    @GetMapping("/expensive/{price}")
    public List<Product> getExpensiveProducts(@PathVariable double price)
    {
        return service.getExpensiveProducts(price);
    }
}