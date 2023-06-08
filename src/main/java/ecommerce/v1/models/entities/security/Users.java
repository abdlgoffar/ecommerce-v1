package ecommerce.v1.models.entities.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ecommerce.v1.models.entities.Wishlists;

import java.util.Collection;
import java.util.Collections;
@Entity
public class Users implements UserDetails {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Getter @Setter
    private String email;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private ROLE role;
    @Getter @Setter
    private String password;
    @OneToOne(mappedBy = "user")
    private Wishlists wishlists;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //this method for create role user or admin
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.role.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
