package com.example.demo.sales.transactions;

import com.example.demo.sales.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    final private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public boolean checkIfExists(Long id){
        return transactionRepository.findById(id).isPresent();
    }

    public List<Transaction> addMultipleTransactions(List<Transaction> transactions, Sale sale){
        List<Transaction> new_trans = new ArrayList<>();
        for (Transaction transaction:transactions) {
            TransactionKey key = new TransactionKey(transaction.getProduct().getId(), sale.getId());
            Transaction trans = new Transaction(key, sale, transaction.getProduct(), transaction.getQuantity(), transaction.getUnit_price());
            new_trans.add(trans);
        }
        transactionRepository.saveAll(new_trans);
        return  transactions;
    }


}
