package ecommerce.v1.controllers;

import ecommerce.v1.models.entities.Wishlists;
import ecommerce.v1.services.WishlistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WishlistsController {
    private WishlistsService wishlistsService;
    @Autowired
    public void setWishlistsService(WishlistsService wishlistsService) {
        this.wishlistsService = wishlistsService;
    }
    @RequestMapping(value = "/wishlists", method = RequestMethod.GET)
    public List<Wishlists> findAll() {
        List<Wishlists> all = this.wishlistsService.findAll();
        return all;
    }

}
