package com.example.demo.sales;


import com.example.demo.clients.ClientService;
import com.example.demo.products.ProductService;
import com.example.demo.sales.transactions.Transaction;
import com.example.demo.sales.transactions.TransactionService;
import com.example.demo.sellers.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/sales")
public class SaleController {

    final private SaleService saleService;
    final private ProductService productService;
    final private SellerService sellerService;
    final private ClientService clientService;
    final private TransactionService transactionService;


    @Autowired
    public SaleController(SaleService saleService, ProductService productService,
                          SellerService sellerService, ClientService clientService,
                            TransactionService transactionService) {
        this.saleService = saleService;
        this.productService = productService;
        this.sellerService = sellerService;
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public List<Sale> getSales(@PathVariable int pageNo,
                               @PathVariable int pageSize) {
        return this.saleService.getAllSales(pageNo, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Sale AddSale(@Valid @RequestBody  Sale s){
        Long client_id = s.getClient().getId();
        Long seller_id = s.getSeller().getId();
        List<Transaction> transactions = s.getSale_transactions();
        if(!clientService.checkIfExists(client_id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "client not found");
        if(!sellerService.checkIfExists(seller_id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "seller not found");
        for (Transaction transaction:transactions) {
            Long product_id = transaction.getProduct().getId();
            if(!productService.checkIfExists(product_id))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product not found");
        }
        s.setId(0L);
        Sale sale = saleService.addOrUpdateSale(s);
        List<Transaction> trans = transactionService.addMultipleTransactions(transactions, sale);
        sale.setCreation_date();
        sale.setSale_transactions(trans);
        return sale;
    }

    @PatchMapping("/{id}")
    public Sale editSaleTransactions(@Valid @RequestBody List<Transaction> transactions, @PathVariable("id") Long id){
        if(!saleService.checkIfExists(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List <Transaction> old_transaction = transactionService.getTransactionBySale(id);
        transactionService.updateTransactions(old_transaction, transactions);
        Sale s = saleService.getSaleById(id);
        Sale sale = saleService.addOrUpdateSale(s);
        return sale;
    }

}
