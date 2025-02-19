package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService; // Import your service
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private ProductController controller;
    private Model model;
    private ProductService service; // Add the service

    @BeforeEach
    void setUp() {
        controller = new ProductController();
        model = Mockito.mock(Model.class);
        service = Mockito.mock(ProductService.class); // Mock the service
        controller.setService(service); // Inject the mock service into the controller. Very important!
    }

    @Test
    void createProductPage_shouldAddProductToModelAndReturnCreateProductView() {
        String viewName = controller.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), argThat(Product.class::isInstance));
    }

    @Test
    void createProductPost_shouldCreateProductAndRedirectToList() {
        Product product = new Product();
        String viewName = controller.createProductPost(product);
        assertEquals("redirect:list", viewName);
        verify(service).create(product); // Verify service.create was called
    }

    @Test
    void productListPage_shouldAddProductsToModelAndReturnProductListView() {
        List<Product> products = new ArrayList<>();
        when(service.findAll()).thenReturn(products); // Stub the service call
        String viewName = controller.productListPage(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", products); // Verify the correct list is added
    }

    @Test
    void editProductPage_shouldAddProductToModelAndReturnEditProductView() {
        Product product = new Product();
        when(service.findById("1L")).thenReturn(product); // Stub the service call
        String viewName = controller.editProductPage("1L", model);
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute("product", product); // Verify correct product is added
    }

    @Test
    void editProductPost_shouldUpdateProductAndRedirectToList() {
        Product product = new Product();
        String viewName = controller.editProductPost("1L", product);
        assertEquals("redirect:/product/list", viewName);
        assertEquals("1L", product.getId()); // Verify the ID is set
        verify(service).update(product); // Verify update is called
    }

    @Test
    void deleteProduct_shouldDeleteProductAndRedirectToList() {
        Product product = new Product();
        when(service.findById("1L")).thenReturn(product);
        String viewName = controller.deleteProduct("1L");
        assertEquals("redirect:/product/list", viewName);
        verify(service).delete(product);
    }
}