package ecommerce.v1.configurations.beans.others;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerPortCustomizerBean implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    //bean for change default server port running.
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(5050);
    }
}
