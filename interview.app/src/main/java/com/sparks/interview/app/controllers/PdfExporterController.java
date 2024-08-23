package com.sparks.interview.app.controllers;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparks.interview.app.models.Product;
import com.sparks.interview.app.services.PdfService;
import com.sparks.interview.app.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/pdf")
@Slf4j
public class PdfExporterController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public void exportToPdf(HttpServletResponse response) throws IOException {
        List<Product> products = productService.getAllProducts();
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        pdfService.export(response,products);
    }


}
