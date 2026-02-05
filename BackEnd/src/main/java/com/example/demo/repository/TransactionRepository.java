package com.example.demo.repository;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTypeOrderByTransactionDateDesc(String type);

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.transactionDate) = ?1 AND MONTH(t.transactionDate) = ?2 ORDER BY t.transactionDate DESC")
    List<Transaction> findByYearAndMonth(int year, int month);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.type = ?1 AND YEAR(t.transactionDate) = ?2 AND MONTH(t.transactionDate) = ?3")
    Integer getTotalByTypeAndMonth(String type, int year, int month);
}
