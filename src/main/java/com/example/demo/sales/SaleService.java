package com.example.demo.sales;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    final private SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    public boolean checkIfExists(Long id){
        return saleRepository.findById(id).isPresent();
    }

    public List<Sale> getAllSales(int pageNo, int pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return this.saleRepository.findAll(paging).toList();
    }

    public Sale getSaleById(Long id) {
        return this.saleRepository.findById(id).get();
    }

    public Sale addOrUpdateSale(Sale s){
        s.setTotal();
        Sale sale = saleRepository.save(s);
        return sale;
    }

}
