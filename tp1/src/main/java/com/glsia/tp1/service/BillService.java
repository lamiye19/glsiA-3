package com.glsia.tp1.service;


import com.glsia.tp1.models.Bill;
import com.glsia.tp1.models.Bill;
import com.glsia.tp1.repository.BillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BillService {

    @Autowired
    private BillRepository billRepository;

    
    public void saveBill(Bill bill)
    {
        billRepository.save(bill);
    }

    
    public List<Bill> showAllBill()
    {
        return billRepository.findAll();
    }

    public Bill selectedBill(int id){
        Optional<Bill> optional = billRepository.findById(id);
        Bill bill = null;
        if (optional.isPresent()){
            bill = optional.get();
        }
        else {
            throw new RuntimeException("id introuvable");
        }

        return bill;
    }


    public void deleteBill(Integer id)
    {
        billRepository.deleteById(id);
    }

}
