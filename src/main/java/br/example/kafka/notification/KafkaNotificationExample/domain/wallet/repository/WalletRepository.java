package br.example.kafka.notification.KafkaNotificationExample.domain.wallet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.example.kafka.notification.KafkaNotificationExample.domain.wallet.model.Wallet;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {}
