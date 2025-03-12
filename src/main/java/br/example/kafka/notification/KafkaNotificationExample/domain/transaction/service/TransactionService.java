package br.example.kafka.notification.KafkaNotificationExample.domain.transaction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.example.kafka.notification.KafkaNotificationExample.domain.notification.service.NotificationService;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.exception.InvalidTransactionException;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.interfaces.ExternalAuthorizationProvider;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.model.Transaction;
import br.example.kafka.notification.KafkaNotificationExample.domain.transaction.repository.TransactionRepository;
import br.example.kafka.notification.KafkaNotificationExample.domain.wallet.model.Wallet;
import br.example.kafka.notification.KafkaNotificationExample.domain.wallet.model.WalletType;
import br.example.kafka.notification.KafkaNotificationExample.domain.wallet.repository.WalletRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final ExternalAuthorizationProvider authorizationProvider;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, ExternalAuthorizationProvider authorizationProvider, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizationProvider = authorizationProvider;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {

        validate(transaction);
                
        Transaction newTransaction = transactionRepository.save(transaction);

        Wallet walletPayer = walletRepository.findById(transaction.payer()).get();
        Wallet walletPayee = walletRepository.findById(transaction.payee()).get();
  
        
        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));
        
        authorizationProvider.isAuthorized(transaction);
        
        notificationService.notify(transaction);

        return newTransaction;
    }

    private void validate(Transaction transaction) {
        Optional<Wallet> payeeOptional = walletRepository.findById(transaction.payee());
        Optional<Wallet> payerOptional = walletRepository.findById(transaction.payer());
    
        if (payerOptional.isEmpty() || payeeOptional.isEmpty()) {
            throw new InvalidTransactionException("Payer or Payee not found");
        }
    
        Wallet payer = payerOptional.get();
        Wallet payee = payeeOptional.get();
    
        if (payer.type() != WalletType.USER.getValue()) {
            throw new InvalidTransactionException("Payer must be of type USER");
        }
    
        if (payer.balance().compareTo(transaction.value()) < 0) {
            throw new InvalidTransactionException("Insufficient balance");
        }
    
        if (payer.id().equals(payee.id())) {
            throw new InvalidTransactionException("Payer and Payee cannot be the same");
        }
    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
    
}
