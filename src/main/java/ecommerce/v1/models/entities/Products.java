package ecommerce.v1.models.entities;

import ecommerce.v1.models.entities.auditing.Audits;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
@EqualsAndHashCode
@Entity
public class Products extends Audits<String> {
    @Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Getter @Setter
    @Column(nullable = false, length = 400)
    private String name;

    @Getter @Setter
    @Column(nullable = false)
    private Long price;

    @Getter @Setter
    @ManyToOne
    private Categories category;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "products_suppliers",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "suppliers_id"))
    private Set<Suppliers> suppliers = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Wishlists> wishlist;

    public Products() {
    }

    public Products(String name, Long price, Categories category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Products(Long id, String name, Long price, Categories category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
