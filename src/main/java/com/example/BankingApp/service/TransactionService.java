package com.example.BankingApp.service;

import com.example.BankingApp.entity.Transaction;
import com.example.BankingApp.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public ResponseEntity<List<Transaction>> fetchAllTransactions() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    public ResponseEntity<Optional<Transaction>> fetchTransactionById(Long transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent())
            return ResponseEntity.ok(transaction);

            return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Transaction> updateTransaction(Long transactionId, Transaction transactionDetails){

        Transaction existingTransaction = transactionRepository.findById(transactionId).orElseThrow(() -> new EntityNotFoundException(String.valueOf(transactionId)));

        existingTransaction.setAmount(transactionDetails.getAmount());
        existingTransaction.setCurrency(transactionDetails.getCurrency());
        existingTransaction.setAccountId(transactionDetails.getAccountId());
        Transaction savedEntity = transactionRepository.save(existingTransaction);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<Object> deleteTransaction(Long transactionId) {

        try{
            transactionRepository.deleteById(transactionId);
            return ResponseEntity.noContent().build();
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }
}
