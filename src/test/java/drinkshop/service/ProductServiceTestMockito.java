package drinkshop.service;

import drinkshop.domain.CategorieBautura;
import drinkshop.domain.Product;
import drinkshop.domain.TipBautura;
import drinkshop.repository.Repository;
import drinkshop.service.validator.ValidationException;
import drinkshop.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTestMockito {
    @Mock
    private Repository<Integer, Product> productRepository;

    @Mock
    private Validator<Product> productValidator;

    @InjectMocks
    private ProductService productService;

    @Test
    public void test_valid() {
        Product validProduct = new Product(1, "test", 4.5, CategorieBautura.TEA, TipBautura.BASIC);

        Mockito.doNothing().when(productValidator).validate(validProduct);

        productService.addProduct(validProduct);

        Mockito.verify(productValidator, Mockito.times(1)).validate(validProduct);
        Mockito.verify(productRepository, Mockito.times(1)).save(validProduct);
    }

    @Test
    public  void test_invalid() {
        // Arrange
        Product invalidProduct = new Product(2, "", -5.0, CategorieBautura.TEA, TipBautura.BASIC);

        // Instruct the mock validator to throw an exception when validating this specific product
        // Note: Change 'RuntimeException.class' to your specific ValidationException class if applicable
        Mockito.doThrow(new ValidationException("Invalid product details!"))
                .when(productValidator).validate(invalidProduct);

        // Act & Assert
        // Verify that the service propagates the exception up
        assertThrows(ValidationException.class, () -> {
            productService.addProduct(invalidProduct);
        });

        // Assert that the repository's save method was NEVER called because validation failed
        Mockito.verify(productRepository, Mockito.never()).save(invalidProduct);
    }

}
