package ecommerce.v1.repositories;

import ecommerce.v1.models.entities.Wishlists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistsRepository extends JpaRepository<Wishlists, Long> {
}
