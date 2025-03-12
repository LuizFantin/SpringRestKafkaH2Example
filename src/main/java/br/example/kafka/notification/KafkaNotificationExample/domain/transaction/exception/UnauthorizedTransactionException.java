package br.example.kafka.notification.KafkaNotificationExample.domain.transaction.exception;

public class UnauthorizedTransactionException extends RuntimeException {
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
