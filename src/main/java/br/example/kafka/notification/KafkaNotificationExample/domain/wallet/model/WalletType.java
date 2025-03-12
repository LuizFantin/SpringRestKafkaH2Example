package br.example.kafka.notification.KafkaNotificationExample.domain.wallet.model;

public enum WalletType {
    USER(1), SELLER(2);
    
    private int value;

    private WalletType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
