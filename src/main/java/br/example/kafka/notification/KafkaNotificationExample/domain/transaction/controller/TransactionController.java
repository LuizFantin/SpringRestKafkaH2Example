package br.example.kafka.notification.KafkaNotificationExample.domain.transaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.model.Transaction;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/transfer")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @GetMapping
    public List<Transaction> listTransactions() {
        return transactionService.getAll();
    }
    
}
