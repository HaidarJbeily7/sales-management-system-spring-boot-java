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

//    public boolean checkIfExists(Long id){
//        return transactionRepository.findById(id).isPresent();
//    }

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

    public List<Transaction> getTransactionBySale(Long sale_id){
        List<Transaction> transactions = transactionRepository.findBySaleId(sale_id);
        return transactions;
    }

    public void updateTransactions(List<Transaction> old_trans, List<Transaction> new_trans){

        for (int index_new = 0; index_new < new_trans.size() ; index_new++){
            Transaction _new = new_trans.get(index_new);
            for (int index_old = 0; index_old < old_trans.size(); index_old++) {
                Transaction _old = old_trans.get(index_old);
                if(_old.equals(_new)){
                    int change_quantity = _new.getQuantity() - _old.getQuantity();
                    double change_price = _new.getUnit_price() - _old.getUnit_price();
                    old_trans.get(index_old).setQuantity(_new.getQuantity());
                    old_trans.get(index_old).setUnit_price(_new.getUnit_price());
                    System.out.println(change_quantity);
                    System.out.println(change_price);
                    System.out.println();
                }
            }
        }
        transactionRepository.saveAll(old_trans);
    }

}
