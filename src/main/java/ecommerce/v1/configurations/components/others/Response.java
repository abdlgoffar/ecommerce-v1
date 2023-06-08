package ecommerce.v1.configurations.components.others;
import ecommerce.v1.helpers.lambda.Function02;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

@Component @Slf4j
public class Response<ENTITY, DTO> {
    @Getter @Setter
    private boolean status;
    @Getter @Setter
    private Set<String> messages = new LinkedHashSet<>();
    @Getter @Setter
    private ENTITY payload;
    public ResponseEntity<Response<ENTITY, DTO>> create(
            Function<ENTITY, ENTITY> service,
            ModelMapper modelMapper,
            Class<ENTITY> entityClass,
            DTO dto,
            Errors errors) {
        Response<ENTITY, DTO> response = new Response<>();
        if (errors.hasErrors()) {
            response.setStatus(false);
            for (int i = 0; i < errors.getAllErrors().size(); i++) {
                response.getMessages().add(errors.getAllErrors().get(i).getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setStatus(true);
        ENTITY map = modelMapper.map(dto, entityClass);
        ENTITY apply = service.apply(map);
        response.setPayload(apply);
        log.info(response.getPayload().toString());
        return ResponseEntity.ok(response);
    }
    public ResponseEntity<Response<ENTITY, DTO>> update(
            Function02<ENTITY, Long, ENTITY> service,
            ModelMapper modelMapper,
            Class<ENTITY> entityClass,
            DTO dto,
            Long id,
            Errors errors) {
        Response<ENTITY, DTO> response = new Response<>();
        if (errors.hasErrors()) {
            response.setStatus(false);
            for (int i = 0; i < errors.getAllErrors().size(); i++) {
                response.getMessages().add(errors.getAllErrors().get(i).getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        ENTITY map = modelMapper.map(dto, entityClass);
        ENTITY apply = service.apply(map, id);
        if (apply == null) {
            response.setStatus(false);
            response.setPayload(null);
            response.getMessages().add("data with id " + id + " not available");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(apply);
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }

}
