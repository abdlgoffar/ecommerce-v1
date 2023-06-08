package ecommerce.v1.configurations.beans.others;

import ecommerce.v1.services.auditing.AuditorAwareService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareBean {
    //bean for handle auditing data
    @Bean
    public AuditorAware<String> auditorAware() {
        AuditorAware<String> auditorAware = new AuditorAwareService();
        return auditorAware;
    }
}
