package ecommerce.v1.repositories;

import ecommerce.v1.models.entities.Products;
import ecommerce.v1.models.entities.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    //custom query example
    @Query("SELECT p FROM Products p WHERE p.name = ?1")
    public List<Products> findProductsByName(String name);
    @Query("SELECT p FROM Products p WHERE p.name LIKE %:name%")
    public List<Products> searchProductsByName(@Param("name") String name);
    @Query("SELECT p FROM Products p WHERE :supplier MEMBER OF p.suppliers ")
    public  List<Products> findProductBySupplier(@Param("supplier") Suppliers supplier);

}
