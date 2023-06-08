package ecommerce.v1.models.entities;

import ecommerce.v1.models.entities.security.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Wishlists {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Getter @Setter
    @Column(nullable = false, length = 400)
    private String name;
    @Getter @Setter
    @OneToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", referencedColumnName = "id", unique = true)
    private Users user;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "wishlists_products",
            joinColumns = @JoinColumn(name = "wishlists_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private Set<Products> products = new LinkedHashSet<>();

}
