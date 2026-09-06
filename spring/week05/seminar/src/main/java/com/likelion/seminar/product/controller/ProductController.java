package com.likelion.seminar.product.controller;

import com.likelion.seminar.product.dto.ProductResponse;
import com.likelion.seminar.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/jpa")
    public List<ProductResponse> findTop10ByPrice() {
        return productService.findTop10ByPrice();
    }

    @GetMapping("/jpql")
    public List<ProductResponse> findTop5ByStock() {
        return productService.findTop5ByStock();
    }


}
