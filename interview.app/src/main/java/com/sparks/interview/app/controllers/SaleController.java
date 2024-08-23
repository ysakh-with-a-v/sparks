package com.sparks.interview.app.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparks.interview.app.models.Sale;
import com.sparks.interview.app.services.SalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/sale")
@Slf4j
public class SaleController {

    @Autowired
    private SalesService salesService;


    @GetMapping
    public ResponseEntity<List<Sale>> getSales() {
        List<Sale> sales = salesService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insertProduct(@RequestBody Sale sale){
        Sale saleInserted = salesService.addSale(sale);
        log.info("The Sale is added to db {}",saleInserted);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long saleId){
        salesService.deleteSale(saleId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
