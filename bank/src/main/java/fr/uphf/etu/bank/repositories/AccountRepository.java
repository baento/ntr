package fr.uphf.etu.bank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uphf.etu.bank.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findById(Integer id);

    Optional<Account> findByOwner(String owner);

    Boolean existsByOwner(String owner);
}
