package ecommerce.v1.controllers;
import ecommerce.v1.configurations.components.others.Response;
import ecommerce.v1.models.dto.suppliers.create.SuppliersDto;
import ecommerce.v1.models.entities.Suppliers;
import ecommerce.v1.services.SuppliersService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
public class SuppliersController {
    private SuppliersService suppliersService;
    private Response<Suppliers, SuppliersDto> responseCreate;
    private Response<Suppliers, ecommerce.v1.models.dto.suppliers.update.SuppliersDto> responseUpdate;
    private ModelMapper modelMapper;
    @Autowired
    public void setSuppliersService(SuppliersService suppliersService) {
        this.suppliersService = suppliersService;
    }
    @Autowired
    public void setResponseCreate(Response<Suppliers, SuppliersDto> responseCreate) {
        this.responseCreate = responseCreate;
    }
    @Autowired
    public void setResponseUpdate(Response<Suppliers, ecommerce.v1.models.dto.suppliers.update.SuppliersDto> responseUpdate) {
        this.responseUpdate = responseUpdate;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @RequestMapping(value = "/suppliers", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid SuppliersDto suppliersDto, Errors errors) {
        return this.responseCreate.create(
                (entity) -> this.suppliersService.create(entity),
                this.modelMapper,
                Suppliers.class,
                suppliersDto,
                errors);
    }
    @RequestMapping(value = "/suppliers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOneById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ecommerce.v1.models.dto.suppliers.update.SuppliersDto suppliersDto,
            Errors errors) {
        return this.responseUpdate.update(
                (entity, entityId) -> this.suppliersService.updateOneById(entity, entityId),
                this.modelMapper,
                Suppliers.class,
                suppliersDto,
                id,
                errors);
    }
    @RequestMapping(value = "/suppliers", method = RequestMethod.GET)
    public Suppliers findByEmail(@RequestBody Suppliers suppliers) {
        Suppliers byEmail = this.suppliersService.findByEmail(suppliers);
        return byEmail;
    }
    @RequestMapping(value = "/suppliers/{id}", method = RequestMethod.DELETE)
    public void hardDeleteOneById(@PathVariable("id") Long id) {
        this.suppliersService.hardDeleteOneById(id);
    }
}
