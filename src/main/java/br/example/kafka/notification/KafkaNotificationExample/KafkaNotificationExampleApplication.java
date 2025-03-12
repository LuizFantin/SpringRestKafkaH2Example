package br.example.kafka.notification.KafkaNotificationExample;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.kafka.config.TopicBuilder;

@EnableJdbcAuditing
@EnableJdbcRepositories
@SpringBootApplication
public class KafkaNotificationExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaNotificationExampleApplication.class, args);
	}

	@Bean
	NewTopic notificationTopic(){
		return TopicBuilder.name("transaction-notification").build();
	}

}
