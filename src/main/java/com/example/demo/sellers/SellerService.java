package com.example.demo.sellers;

import com.example.demo.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    final private SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public boolean checkIfExists(Long id){
        return sellerRepository.findById(id).isPresent();
    }

    public Category getSellerById(Long id){return sellerRepository.findById(id).get();}
}
