package br.example.kafka.notification.KafkaNotificationExample.domain.transaction.exception;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
}
