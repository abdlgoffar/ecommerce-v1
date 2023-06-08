package ecommerce.v1.services.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import ecommerce.v1.models.entities.security.Users;

import java.util.Optional;

public class AuditorAwareService implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(users.getEmail());
    }
}
