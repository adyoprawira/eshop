package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6"); // Example UUID
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096"); // Example UUID
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
    }

    @Test
    void testCreateProduct() {
        productService.create(product1);
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).create(productCaptor.capture());
        Product capturedProduct = productCaptor.getValue();
        assertEquals(product1.getProductName(), capturedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), capturedProduct.getProductQuantity());
        assertNotNull(capturedProduct.getProductId()); // Check if ID is generated
    }


    @Test
    void testFindAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productRepository.findAll()).thenReturn(products.iterator());

        List<Product> foundProducts = productService.findAll();

        assertEquals(2, foundProducts.size());
        assertEquals(product1.getProductId(), foundProducts.get(0).getProductId());
        assertEquals(product2.getProductId(), foundProducts.get(1).getProductId());
    }

    @Test
    void testFindProductById() {
        when(productRepository.findById(product1.getProductId())).thenReturn(product1);

        Product foundProduct = productService.findById(product1.getProductId());

        assertNotNull(foundProduct);
        assertEquals(product1.getProductId(), foundProduct.getProductId());
    }

    @Test
    void testUpdateProduct() {
        product1.setProductName("Updated Sampo");
        product1.setProductQuantity(150);

        when(productRepository.update(product1)).thenReturn(product1); // Stub update

        Product updatedProduct = productService.update(product1);

        assertEquals("Updated Sampo", updatedProduct.getProductName());
        assertEquals(150, updatedProduct.getProductQuantity());
        verify(productRepository).update(product1); // Verify update called
    }

    @Test
    void testDeleteProduct() {
        productService.delete(product1);
        verify(productRepository).delete(product1);
    }
}