package ecommerce.v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.v1.models.entities.Products;
import ecommerce.v1.models.entities.Suppliers;
import ecommerce.v1.repositories.ProductsRepository;
import ecommerce.v1.repositories.SuppliersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SuppliersService {
    private SuppliersRepository suppliersRepository;
    private ProductsRepository productsRepository;
    @Autowired
    public SuppliersService(SuppliersRepository suppliersRepository, ProductsRepository productsRepository) {
        this.suppliersRepository = suppliersRepository;
        this.productsRepository = productsRepository;
    }
    public Suppliers create(Suppliers suppliers) {
        Suppliers dataSuppliers = new Suppliers(suppliers.getName(), suppliers.getEmail());
        Suppliers saveSuppliers = this.suppliersRepository.save(dataSuppliers);
        Suppliers result = new Suppliers(saveSuppliers.getId(), saveSuppliers.getName(), saveSuppliers.getEmail());
        if (suppliers.getProducts() != null) {
            if (suppliers.getProducts().isEmpty() == false) {
                for (Products product : suppliers.getProducts()) {
                    Products dataProducts = new Products(product.getName(), product.getPrice(), product.getCategory());
                    dataProducts.getSuppliers().add(saveSuppliers);
                    Products saveProducts = this.productsRepository.save(dataProducts);
                    Products products = new Products(saveProducts.getId(), saveProducts.getName(), saveProducts.getPrice(), saveProducts.getCategory());
                    result.getProducts().add(products);
                }
            }
        }
        return result;
    }
    public Suppliers updateOneById(Suppliers suppliers, Long id) {
        Optional<Suppliers> byId = this.suppliersRepository.findById(id);
        if (byId.isPresent()) {
            Suppliers suppliersUpdate = byId.get();
            suppliersUpdate.setName(suppliers.getName());
            suppliersUpdate.setEmail(suppliers.getEmail());
            Suppliers saveSuppliers = this.suppliersRepository.save(suppliersUpdate);
            return saveSuppliers;
        } else {
            return null;
        }
    }
    public Suppliers findByEmail(Suppliers suppliers) {
        List<Products> allProducts = this.productsRepository.findAll();
        Suppliers byEmail = this.suppliersRepository.findByEmail(suppliers.getEmail());
        for (int i = 0; i < allProducts.size(); i++) {
            Products products = new Products(allProducts.get(i).getId(), allProducts.get(i).getName(), allProducts.get(i).getPrice(), allProducts.get(i).getCategory());
            for (Suppliers supplier : allProducts.get(i).getSuppliers()) {
                if (byEmail.getEmail().equals(supplier.getEmail())) {
                    byEmail.getProducts().add(products);
                }
            }
        }
        return byEmail;
    }
    public void hardDeleteOneById(Long id) {
        Optional<Suppliers> byId = this.suppliersRepository.findById(id);
        if (id != null && byId.isPresent()) {
            this.suppliersRepository.deleteById(id);
        }
    }
}
