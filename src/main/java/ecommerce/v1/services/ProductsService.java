package ecommerce.v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ecommerce.v1.models.entities.Products;
import ecommerce.v1.models.entities.Suppliers;
import ecommerce.v1.repositories.ProductsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;
    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Products create(Products products) {
        Products saveProducts = this.productsRepository.save(products);
        return saveProducts;
    }
    public Products updateOneById(Products products, Long id) {
        Optional<Products> byId = this.productsRepository.findById(id);
        if (byId.isPresent()) {
            Products productsUpdate = byId.get();
            productsUpdate.setName(products.getName());
            productsUpdate.setPrice(products.getPrice());
            productsUpdate.setCategory(products.getCategory());
            Products saveProducts = this.productsRepository.save(productsUpdate);
            return saveProducts;
        } else {
            return null;
        }
    }
    public List<Products> findByName(Products products) {
        List<Products> productsByName = this.productsRepository.findProductsByName(products.getName());
        return productsByName;
    }
    public List<Products> searchByName(Products products) {
        List<Products> productsByName = this.productsRepository.searchProductsByName(products.getName());
        return productsByName;
    }
    public List<Products> findBySupplier(Products products) {
        List<Suppliers> suppliersList = new ArrayList<>();
        for (Suppliers supplier : products.getSuppliers()) {
            suppliersList.add(supplier);
        }
        List<Products> productBySupplier = this.productsRepository.findProductBySupplier(suppliersList.get(0));
        return productBySupplier;
    }
    public Iterable<Products> findAllAndPagingSorting(Pageable pageable) {
        Page<Products> all = this.productsRepository.findAll(pageable);
        return all;
    }
    public void hardDeleteOneById(Long id) {
        Optional<Products> byId = this.productsRepository.findById(id);
        if (id != null && byId.isPresent()) {
            this.productsRepository.deleteById(id);
        }
    }
    public List<Products> findAll() {
        List<Products> all = this.productsRepository.findAll();
        return all;
    }
}

