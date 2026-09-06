package com.likelion.seminar.product.repository;

import com.likelion.seminar.product.domain.Product;
import com.likelion.seminar.product.domain.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        productRepository.saveAll(List.of(
                Product.create("볼펜", 1000, 50),
                Product.create("형광펜", 1500, 30),
                Product.create("색연필", 2000, 20),
                Product.create("샤프", 2500, 25),
                Product.create("노트", 3000, 15),
                Product.create("지우개", 500, 10),
                Product.create("만년필", 12000, 5),
                Product.create("수성펜", 1800, 80),
                Product.create("유성펜", 1900, 70),
                Product.create("붓펜", 1300, 60),
                Product.create("젤펜", 1700, 40),
                Product.create("마커펜", 2100, 90),
                Product.create("사인펜", 2200, 12),
                Product.create("네임펜", 2300, 13),
                Product.create("캘리펜", 2400, 14),
                Product.create("브러시펜", 2600, 16)
        ));
    }

    @Test
    void 가격이_비싼_상품_상위_10개를_조회한다() {
        List<Product> products =
                productRepository.findTop10ByOrderByPriceDesc();

        assertThat(products).hasSize(10);

        assertThat(products)
                .extracting(Product::getPrice)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void 가격이_2000원_이하인_상품_중_재고가_많은_5개를_조회한다() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Product> products = productRepository.findTopProductsByJpql(2000, pageable);

        assertThat(products).hasSize(5);

        assertThat(products)
                .extracting(Product::getName)
                .containsExactly(
                        "수성펜",
                        "유성펜",
                        "붓펜",
                        "볼펜",
                        "젤펜"
                );

        assertThat(products)
                .allMatch(product -> product.getPrice() <= 2000);
    }

    @Test
    void 이름에_펜이_포함된_상품을_가격이_저렴한_순으로_10개_조회한다() {
        JPAQueryFactory queryFactory =
                new JPAQueryFactory(entityManager);

        QProduct product = QProduct.product;

        List<Product> products = queryFactory
                .selectFrom(product)
                .where(product.name.contains("펜"))
                .orderBy(product.price.asc())
                .limit(10)
                .fetch();

        assertThat(products).hasSize(10);

        assertThat(products)
                .allMatch(item -> item.getName().contains("펜"));

        assertThat(products)
                .extracting(Product::getPrice)
                .isSorted();
    }
}