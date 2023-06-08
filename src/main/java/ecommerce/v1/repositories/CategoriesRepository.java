package ecommerce.v1.repositories;

import ecommerce.v1.models.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    @Override//override find all method for the sake of soft delete concept
    @Query("SELECT c FROM Categories c WHERE c.hidden = false")
    List<Categories> findAll();
}
