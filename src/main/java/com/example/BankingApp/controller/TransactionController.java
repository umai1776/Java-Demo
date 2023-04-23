package com.example.BankingApp.controller;

import com.example.BankingApp.entity.Transaction;
import com.example.BankingApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(newTransaction);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return transactionService.fetchAllTransactions();
    }

    @GetMapping("transactions/{id}")
    public ResponseEntity<Optional<Transaction>> getTransaction(@PathVariable(value = "id") final long transactionId){
        return transactionService.fetchTransactionById(transactionId);
    }

    @PutMapping("transactions/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable(value = "id") long transactionId, @RequestBody Transaction transaction){
        return transactionService.updateTransaction(transactionId,transaction);
    }

    @DeleteMapping("transactions/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable(value = "id") final long transactionId){
        return transactionService.deleteTransaction(transactionId);
    }

}
