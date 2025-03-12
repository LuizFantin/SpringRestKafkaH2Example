package br.example.kafka.notification.KafkaNotificationExample.domain.notification.exception;

public class NotificationException extends RuntimeException {
    public NotificationException(String message) {
        super(message);
    }
}
