package fr.uphf.etu.merchant.services;

import java.util.Map;

import fr.uphf.etu.merchant.models.Product;

public interface CartService {
    public void addProduct(Product product);

    public void addProduct(Product product, int quantity);

    public void removeProduct(Product product);

    public void removeProduct(Product product, int quantity);

    public void clear();

    Map<Product, Integer> getProducts();

    Double getTotal();
}
