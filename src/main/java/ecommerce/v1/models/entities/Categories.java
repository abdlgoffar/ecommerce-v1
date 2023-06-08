package ecommerce.v1.models.entities;

import ecommerce.v1.models.entities.auditing.Audits;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Entity @ToString
public class Categories extends Audits<String> {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Getter @Setter
    @Column(nullable = false, length = 400, unique = true)
    private String name;
    @Getter @Setter
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean hidden;
}
