package br.example.kafka.notification.KafkaNotificationExample.infra.transaction;

public record ExternalAuthorizationResponse (
    String status,
    AuthorizationData data
) {

    public record AuthorizationData(boolean authorization) {}
}
