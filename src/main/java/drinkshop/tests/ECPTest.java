package drinkshop.tests;

import drinkshop.domain.CategorieBautura;
import drinkshop.domain.Product;
import drinkshop.domain.TipBautura;
import drinkshop.repository.Repository;
import drinkshop.repository.file.FileProductRepository;
import drinkshop.service.ProductService;

public class ECPTest {

    public static void runSaveProductTest() {
        Repository<Integer, Product> productRepo = new FileProductRepository("data/products.txt");
        ProductService productService = new ProductService(productRepo);
        Product p1 = new Product(0,"nume",8.0, CategorieBautura.BUBBLE_TEA, TipBautura.WATER_BASED);
        Product p2 = new Product(1,"nume",8.0, null, TipBautura.WATER_BASED);
        Product p3 = new Product(2, "nume", Double.parseDouble("pret"), CategorieBautura.BUBBLE_TEA, TipBautura.WATER_BASED);

        productService.addProduct(p1);
        productService.addProduct(p2);

        try {
            productService.addProduct(p3);
            assert(false);
        } catch (NumberFormatException e) {
            assert(productService.findById(2)==null);
        }

        assert(productService.findById(0)==p1);
        assert(productService.findById(1)==p2);
    }
}
