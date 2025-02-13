package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(Long id) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(id.toString()))
                .findFirst()
                .orElse(null);
    }

    public Product update(Product product) {
        Product productToUpdate = productData.stream()
                .filter(p -> p.getProductId().equals(product.getProductId()))
                .findFirst()
                .orElse(null);

        if (productToUpdate == null) {  // Check if productToUpdate is null
            throw new NoSuchElementException("Product not found for update");
        }

        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setProductQuantity(product.getProductQuantity());
        return productToUpdate;
    }

    public void delete(Product product) {
        boolean removed = productData.removeIf(p -> p.getProductId().equals(product.getProductId())); // Use removeIf
        if (!removed) { // Check if anything was removed
            throw new NoSuchElementException("Product not found for delete");
        }
    }
}