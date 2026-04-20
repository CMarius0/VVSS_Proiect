package drinkshop.repository.file;

import drinkshop.domain.*;
import drinkshop.repository.Repository;
import drinkshop.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileOrderRepositoryTest {
    FileOrderRepository repo;
    @BeforeEach
    void setUp() {
        Repository<Integer, Product> productRepo = new FileProductRepository("data/test_products.txt");
        repo = new FileOrderRepository("data/test_orders.txt",productRepo);
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void createEntityAsString() {
        Order order1 = new Order(1);
        assertEquals("1,zero,0.0", repo.createEntityAsString(order1));
        order1.addItem(new OrderItem(new Product(1, "M", 0.0, CategorieBautura.TEA, TipBautura.BASIC), 1));
        assertEquals("1,1:1one,0.0", repo.createEntityAsString(order1));
        order1.addItem(new OrderItem(new Product(2, "M", 0.0, CategorieBautura.TEA, TipBautura.BASIC), 1));
        assertEquals("1,1:1|2:1two,0.0", repo.createEntityAsString(order1));
        order1.addItem(new OrderItem(new Product(3, "M", 0.0, CategorieBautura.TEA, TipBautura.BASIC), 1));
        assertEquals("1,1:1|2:1|3:1three,0.0", repo.createEntityAsString(order1));
        order1.addItem(new OrderItem(new Product(4, "M", 0.0, CategorieBautura.TEA, TipBautura.BASIC), 1));
        assertEquals("1,1:1|2:1|3:1|4:1four,0.0", repo.createEntityAsString(order1));
        order1.addItem(new OrderItem(new Product(5, "M", 0.0, CategorieBautura.TEA, TipBautura.BASIC), 1));
        assertEquals("1,1:1|2:1|3:1|4:1|5:1,0.0", repo.createEntityAsString(order1));
    }
}