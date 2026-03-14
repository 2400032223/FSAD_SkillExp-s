package com.klu.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.exception.ProductNotFoundException;
import com.klu.model.Product;
import com.klu.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // Find by ID using Optional
    public Product getProductById(Long id) {

        Optional<Product> product = repository.findById(id);

        if(product.isPresent()) 
        {
            return product.get();
        } 
        else 
        {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }

    public List<Product> getByCategory(String category)
    {
        return repository.findByCategory(category);
    }

    public List<Product> getByPriceRange(double min,double max)
    {
        return repository.findByPriceBetween(min,max);
    }

    public List<Product> getSortedProducts()
    {
        return repository.sortByPrice();
    }

    public List<Product> getExpensiveProducts(double price)
    {
        return repository.findExpensiveProducts(price);
    }

}