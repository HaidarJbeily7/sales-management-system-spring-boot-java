package com.example.demo.sales;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    final private SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return this.saleRepository.findAll();
    }
    public Sale getSaleById(Long id) {
        return this.saleRepository.findById(id).get();
    }

    public Sale addNewSale(Sale s){
        s.setTotal();
        saleRepository.save(s);
        return s;
    }
}
