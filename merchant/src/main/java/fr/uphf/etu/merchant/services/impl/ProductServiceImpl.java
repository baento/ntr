package fr.uphf.etu.merchant.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.uphf.etu.merchant.exceptions.ResourceNotFoundException;
import fr.uphf.etu.merchant.models.Product;
import fr.uphf.etu.merchant.repositories.ProductRepository;
import fr.uphf.etu.merchant.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }
}
