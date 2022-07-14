package com.example.demo.sales.transactions;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, TransactionKey> {

    List<Transaction> findBySaleId(Long sale_id);
}
