package ecommerce.v1.models.entities;

import ecommerce.v1.models.entities.auditing.Audits;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Suppliers extends Audits<String> {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Getter @Setter
    @Column(nullable = false, length = 400)
    private String name;

    @Getter @Setter
    @Column(nullable = false, length = 400, unique = true)
    private String email;
    @ManyToMany(mappedBy = "suppliers")
    private Set<Products> product;
    @Getter @Setter
    @Transient
    private Set<Products> products = new LinkedHashSet<>();

    public Suppliers() {
    }

    public Suppliers(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Suppliers(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Suppliers suppliers = (Suppliers) o;

        if (!id.equals(suppliers.id)) return false;
        if (!Objects.equals(name, suppliers.name)) return false;
        return Objects.equals(email, suppliers.email);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
