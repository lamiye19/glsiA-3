package com.glsia.tp1.service;

import com.glsia.tp1.models.Customer;
import com.glsia.tp1.models.Sale;
import com.glsia.tp1.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public void saveCustomer(Customer customer)
    {
            customerRepository.save(customer);
    }


    public List<Customer> showAllCustomer()
    {
        return customerRepository.findAll();
    }


    public Customer selectedCustomer(Integer id){
        Optional<Customer> optional = customerRepository.findById(id);
        Customer customer = null;
        if (optional.isPresent()){
            customer = optional.get();
        }
        else {
            throw new RuntimeException("id introuvable");
        }

        return customer;
    }

    public void deleteCustomer(Integer id){
        if (id == null)
        {
            log.error("Customer Id is null");
            return;
        }
        customerRepository.deleteById(id);
    }

}
