package ecommerce.v1.repositories;

import ecommerce.v1.models.entities.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {
    //derived query example
    public Suppliers findByEmail(String email);
}
