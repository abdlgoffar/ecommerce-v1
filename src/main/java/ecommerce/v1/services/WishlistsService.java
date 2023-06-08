package ecommerce.v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.v1.models.entities.Wishlists;
import ecommerce.v1.repositories.WishlistsRepository;

import java.util.List;

@Service
public class WishlistsService {
    private WishlistsRepository wishlistsRepository;

    @Autowired
    public void setWishlistsRepository(WishlistsRepository wishlistsRepository) {
        this.wishlistsRepository = wishlistsRepository;
    }
    public List<Wishlists> findAll() {
        List<Wishlists> all = this.wishlistsRepository.findAll();
        return all;
    }
    public Wishlists create(Wishlists wishlists)  {
        Wishlists saveWishlists = this.wishlistsRepository.save(wishlists);
        return saveWishlists;
    }
}
