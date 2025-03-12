package br.example.kafka.notification.KafkaNotificationExample.domain.transaction.interfaces;

import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.exception.UnauthorizedTransactionException;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.model.Transaction;

public interface ExternalAuthorizationProvider {
    boolean isAuthorized(Transaction transaction) throws UnauthorizedTransactionException;
}
