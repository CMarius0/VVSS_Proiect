package drinkshop.service;

import drinkshop.domain.CategorieBautura;
import drinkshop.domain.Product;
import drinkshop.domain.TipBautura;
import drinkshop.repository.Repository;
import drinkshop.service.validator.ProductValidator;
import drinkshop.service.validator.ValidationException;
import drinkshop.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationTestingStep2 {

    private Repository<Integer, Product> productRepository;

    private Validator<Product> productValidator;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productRepository = (Repository<Integer, Product>) Mockito.mock(Repository.class);
        productValidator = new ProductValidator();
        productService = new ProductService(productRepository, productValidator);
    }

    @Test
    public void test_valid() {
        Product validProduct = new Product(1, "test", 4.5, CategorieBautura.TEA, TipBautura.BASIC);

        productService.addProduct(validProduct);

        Mockito.verify(productRepository, Mockito.times(1)).save(validProduct);
    }

    @Test
    public  void test_invalid() {
        Product invalidProduct = new Product(2, "", -5.0, CategorieBautura.TEA, TipBautura.BASIC);

        assertThrows(ValidationException.class, () -> {
            productService.addProduct(invalidProduct);
        });

        Mockito.verify(productRepository, Mockito.never()).save(invalidProduct);
    }
}
