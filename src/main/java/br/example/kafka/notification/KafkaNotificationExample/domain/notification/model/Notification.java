package br.example.kafka.notification.KafkaNotificationExample.domain.notification.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("NOTIFICATIONS")
public record Notification(String status, String message) {
    public boolean isError() {
        return "error".equalsIgnoreCase(status);
    }

    public boolean isSuccess() {
        return "204".equals(status);
    }
}
