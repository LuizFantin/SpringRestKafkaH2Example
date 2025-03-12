package br.example.kafka.notification.KafkaNotificationExample.infra.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.exception.UnauthorizedTransactionException;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.interfaces.ExternalAuthorizationProvider;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.model.Transaction;

@Service
public class MockExternalAuthorizationProvider implements ExternalAuthorizationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockExternalAuthorizationProvider.class);
    private RestClient restClient;

    public MockExternalAuthorizationProvider(RestClient.Builder builder) {
        this.restClient = builder
        .baseUrl("https://util.devi.tools/api/v2/authorize")
        .build();
    }

    @Override
    public boolean isAuthorized(Transaction transaction) throws UnauthorizedTransactionException {
        LOGGER.info("Checking authorization for transaction: {}", transaction);

        try {
            ResponseEntity<ExternalAuthorizationResponse> externalAuthorizationResponse = restClient.get()
                .retrieve()
                .toEntity(ExternalAuthorizationResponse.class);

            if (externalAuthorizationResponse != null && externalAuthorizationResponse.getBody() != null) {
                LOGGER.info(externalAuthorizationResponse.getBody().toString());
                if (!externalAuthorizationResponse.getBody().data().authorization()) {
                    throw new UnauthorizedTransactionException("Unauthorized transaction: Authorization failed");
                }
            }

            LOGGER.info("Transaction authorized");
            return true;
        } catch (HttpClientErrorException.Forbidden ex) {
            throw new UnauthorizedTransactionException("Unauthorized transaction: Forbidden");
        } catch (Exception ex) {
            throw new UnauthorizedTransactionException("Unexpected error occurred");
        }
    }


}
