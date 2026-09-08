package com.likelion.seminar.product.service;

import com.likelion.seminar.product.dto.ProductResponse;
import com.likelion.seminar.product.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> findTop10ByPrice() {
        return productRepository.findTop10ByOrderByPriceDesc()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    public List<ProductResponse> findTop5ByStock() {
        Pageable pageable = PageRequest.of(0, 5);

        return productRepository.findTopProductsByJpql(
                        2000,
                        pageable
                )
                .stream()
                .map(ProductResponse::from)
                .toList();
    }
}

