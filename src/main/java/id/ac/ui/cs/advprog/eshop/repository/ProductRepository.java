package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        if (productToUpdate != null) {
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setProductQuantity(product.getProductQuantity());
        }

        return productToUpdate;
    }
}
