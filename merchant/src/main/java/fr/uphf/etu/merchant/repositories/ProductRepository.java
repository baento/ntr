package fr.uphf.etu.merchant.repositories;

import fr.uphf.etu.merchant.models.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
