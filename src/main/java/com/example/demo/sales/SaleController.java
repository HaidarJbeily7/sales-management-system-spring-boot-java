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

    @GetMapping
    public List<Sale> getSales() {
        return this.saleService.getAllSales();
    }
    
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
        s.setTotal();
        Sale sale = saleService.addNewSale(s);
        transactionService.addMultipleTransactions(transactions, sale);
        return sale;
    }

}
