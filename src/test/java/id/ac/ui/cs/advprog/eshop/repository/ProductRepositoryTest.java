package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setup() {
        product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
    }

    @Test
    void testCreateAndFind() {
        productRepository.create(product1);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product1.getProductName(), savedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        productRepository.create(product1);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct_positive() {
        productRepository.create(product1);
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6"); // Same ID
        updatedProduct.setProductName("Sampo Cap Bambang Updated");
        updatedProduct.setProductQuantity(150);

        productRepository.update(updatedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(updatedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditProduct_negative_productNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("nonexistent-id"); // Nonexistent ID

        assertThrows(NoSuchElementException.class, () -> productRepository.update(updatedProduct));
    }


    @Test
    void testDeleteProduct_positive() {
        productRepository.create(product1);
        productRepository.delete(product1);
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProduct_negative_productNotFound() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("nonexistent-id");

        assertThrows(NoSuchElementException.class, () -> productRepository.delete(nonExistentProduct));
    }

    @Test
    void testFindById_positive() {
        productRepository.create(product1);
        Product foundProduct = productRepository.findById(product1.getProductId());
        assertNotNull(foundProduct);
        assertEquals(product1.getProductId(), foundProduct.getProductId());
        assertEquals(product1.getProductName(), foundProduct.getProductName());
        assertEquals(product1.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testFindById_negative_productNotFound() {
        Product foundProduct = productRepository.findById("nonexistent-id");
        assertNull(foundProduct);
    }
}