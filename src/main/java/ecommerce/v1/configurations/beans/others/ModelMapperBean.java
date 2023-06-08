package ecommerce.v1.configurations.beans.others;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {
    //bean for mapping data object
    @Bean
    public ModelMapper ModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
