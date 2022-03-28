package com.java.tp1.service;

import com.java.tp1.model.Product;
import com.java.tp1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository ProductRepository;

    public void saveProduct(Product Product){
        ProductRepository.save(Product);
    }

    public List<Product> showAllProduct(){
        return ProductRepository.findAll();
    }

    public Product selectedProduct(int id){
        Optional<Product> optional = ProductRepository.findById(id);
        Product Product = null;
        if (optional.isPresent()){
            Product = optional.get();
        }
        else {
            throw new RuntimeException("id introuvable");
        }

        return Product;
    }

    public void deleteProduct(int id){
        ProductRepository.deleteById(id);
    }
}
