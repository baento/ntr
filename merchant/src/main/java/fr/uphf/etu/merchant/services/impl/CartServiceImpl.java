package fr.uphf.etu.merchant.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import fr.uphf.etu.merchant.models.Product;
import fr.uphf.etu.merchant.services.CartService;

@Service
@SessionScope
public class CartServiceImpl implements CartService {

    private Map<Product, Integer> products = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        this.addProduct(product, 1);
    }

    @Override
    public void addProduct(Product product, int quantity) {
        products.merge(product, quantity, Math::addExact);
    }

    @Override
    public void removeProduct(Product product) {
        this.removeProduct(product, 1);
    }

    @Override
    public void removeProduct(Product product, int quantity) {
        int amount = products.merge(product, -quantity, Math::addExact);

        if (amount <= 0) {
            products.remove(product);
        }
    }

    public void clear() {
        products.clear();
    }

    @Override
    public Map<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public Double getTotal() {
        return products.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
    }
}
