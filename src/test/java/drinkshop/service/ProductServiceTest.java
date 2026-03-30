package drinkshop.service;

import drinkshop.domain.Product;
import drinkshop.repository.Repository;
import drinkshop.repository.file.FileProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    ProductService productService;
    @BeforeEach
    void setUp() {
        Repository<Integer, Product> productRepo = new FileProductRepository("data/products.txt");
        productService = new ProductService(productRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct() {
    }
}