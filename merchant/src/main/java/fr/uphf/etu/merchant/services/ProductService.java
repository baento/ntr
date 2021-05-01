package fr.uphf.etu.merchant.services;

import fr.uphf.etu.merchant.models.Product;
import java.util.List;

public interface ProductService {
    public List<Product> getAll();

    public Product getProduct(int id);
}