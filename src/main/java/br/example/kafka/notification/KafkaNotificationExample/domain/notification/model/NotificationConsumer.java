package br.example.kafka.notification.KafkaNotificationExample.domain.notification.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.example.kafka.notification.KafkaNotificationExample.domain.notification.exception.NotificationException;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.model.Transaction;

@Service
public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    private RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder
        .baseUrl("https://run.mocky.io/v3/d187e85c-4813-41f1-8b8e-683ddc084303") // URL for sucess
        // .baseUrl("https://run.mocky.io/v3/9fd73f51-83fd-41a5-969d-47ad76d7e8a7") // URL for error
        .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "KafkaNotificationExample")
    public void receiveNotification(Transaction transaction) {
        LOGGER.info("Notifying for transaction: {}", transaction);
        ResponseEntity<Notification> notification = restClient.get()
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, (_, response) -> {
            LOGGER.error("Error sending notification: {}", response.getBody());
            throw new NotificationException("Error sending notification");
        })
        .onStatus(HttpStatusCode::is5xxServerError, (_, response) -> {
            LOGGER.error("Error sending notification: {}", response.getBody());
            throw new NotificationException("Error sending notification");
        })
        .toEntity(Notification.class);

        LOGGER.info("Notification sent {}...", notification.getStatusCode());
    }
}
