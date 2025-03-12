package br.example.kafka.notification.KafkaNotificationExample.domain.transaction.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.model.Transaction;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long>{}
