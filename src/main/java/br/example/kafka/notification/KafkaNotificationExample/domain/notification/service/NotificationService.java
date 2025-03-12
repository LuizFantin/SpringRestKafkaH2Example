package br.example.kafka.notification.KafkaNotificationExample.domain.notification.service;

import org.springframework.stereotype.Service;

import br.example.kafka.notification.KafkaNotificationExample.domain.notification.model.NotificationProducer;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.model.Transaction;

@Service
public class NotificationService {

    private NotificationProducer notificationProducer;

    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    public void notify(Transaction transaction) {
        notificationProducer.sendNotification(transaction);
    }
}
