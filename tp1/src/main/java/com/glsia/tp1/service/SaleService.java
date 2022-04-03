package com.glsia.tp1.service;

import com.glsia.tp1.models.Sale;
import com.glsia.tp1.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    SaleRepository saleRepository;
    
    public void saveSale(Sale sale){
        saleRepository.save(sale);
    }

    public List<Sale> showAllSale(){
        return saleRepository.findAll();
    }

    public Sale selectedSale(int id){
        Optional<Sale> optional = saleRepository.findById(id);
        Sale Sale = null;
        if (optional.isPresent()){
            Sale = optional.get();
        }
        else {
            throw new RuntimeException("id introuvable");
        }

        return Sale;
    }

    public void deleteSale(int id){
        saleRepository.deleteById(id);
    }
}
