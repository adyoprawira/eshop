package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public Product findById(String id) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = productData.stream()
                .filter(p -> p.getProductId().equals(product.getProductId()))
                .findFirst()
                .orElse(null);

        if (productToUpdate == null) {
            throw new NoSuchElementException("Product not found for update");
        }

        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setProductQuantity(product.getProductQuantity());
        return productToUpdate;
    }

    @Override
    public void delete(Product product) {
        boolean removed = productData.removeIf(p -> p.getProductId().equals(product.getProductId()));
        if (!removed) {
            throw new NoSuchElementException("Product not found for delete");
        }
    }
}