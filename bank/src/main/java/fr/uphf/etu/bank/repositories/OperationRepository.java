package fr.uphf.etu.bank.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uphf.etu.bank.models.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {
    Optional<Operation> findById(Integer id);

    Collection<Operation> findByAccountId(String id);
}
