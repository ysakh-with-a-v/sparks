package com.sparks.interview.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparks.interview.app.models.Sale;
import com.sparks.interview.app.models.SalesRepository;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<Sale> getAllSales(){
        return salesRepository.findAll();
    }

    public Sale addSale(Sale sale){
        return salesRepository.save(sale);
    }

    public void deleteSale(long id){
        salesRepository.deleteById(id);
    }
}
