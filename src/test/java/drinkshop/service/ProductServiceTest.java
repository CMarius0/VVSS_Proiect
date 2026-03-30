package drinkshop.service;

import drinkshop.domain.CategorieBautura;
import drinkshop.domain.Product;
import drinkshop.domain.TipBautura;
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
        Repository<Integer, Product> productRepo = new FileProductRepository("data/test_products.txt");
        productService = new ProductService(productRepo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addProduct() {
        assertThrows(Exception.class, ()->{productService.addProduct(new Product(1,"", 0.0, CategorieBautura.TEA, TipBautura.BASIC));});

        Product p1 = new Product(1,"M", 0.0, CategorieBautura.TEA, TipBautura.BASIC);
        productService.addProduct(p1);
        assertEquals(p1, productService.findById(1));

        assertThrows(Exception.class, ()->{productService.addProduct(new Product(2,"M", -1.0, CategorieBautura.TEA, TipBautura.BASIC));});

        Product p2 = new Product(2,"M", 0.0, CategorieBautura.TEA, TipBautura.BASIC);
        productService.addProduct(p2);
        assertEquals(p2, productService.findById(2));



    }

    @Test
    void addEcpProduc(){
        Product p1 = new Product(0,"nume",8.0, CategorieBautura.BUBBLE_TEA, TipBautura.WATER_BASED);
        Product p2 = new Product(1,"nume",8.0, null, TipBautura.WATER_BASED);
        Product p3 = new Product(2, "nume", -1.0, CategorieBautura.BUBBLE_TEA, TipBautura.WATER_BASED);

        productService.addProduct(p1);
        productService.addProduct(p2);

        assertThrows(Exception.class, ()->{productService.addProduct(p3);});

        assertEquals(p1, productService.findById(0));
        assertEquals(p2, productService.findById(1));
    }
}