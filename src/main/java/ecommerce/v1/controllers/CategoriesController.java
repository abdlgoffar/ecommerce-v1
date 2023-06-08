package ecommerce.v1.controllers;

import ecommerce.v1.configurations.components.others.Response;
import ecommerce.v1.models.dto.categories.create.CategoriesDto;
import ecommerce.v1.models.entities.Categories;
import ecommerce.v1.services.CategoriesService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriesController {
    private CategoriesService categoriesService;
    private Response<Categories, CategoriesDto> responseCreate;
    private Response<Categories, ecommerce.v1.models.dto.categories.update.CategoriesDto> responseUpdate;
    private ModelMapper modelMapper;
    @Autowired
    public void setCategoriesService(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }
    @Autowired
    public void setResponseCreate(Response<Categories, CategoriesDto> responseCreate) {
        this.responseCreate = responseCreate;
    }
    @Autowired
    public void setResponseUpdate(Response<Categories, ecommerce.v1.models.dto.categories.update.CategoriesDto> responseUpdate) {
        this.responseUpdate = responseUpdate;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid CategoriesDto categoriesDto, Errors errors) {
        return this.responseCreate.create(
                (entity) -> this.categoriesService.create(entity),
                this.modelMapper,
                Categories.class,
                categoriesDto,
                errors);
    }
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOneById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ecommerce.v1.models.dto.categories.update.CategoriesDto categoriesDto,
            Errors errors){
        return this.responseUpdate.update(
                (entity, entityId) -> this.categoriesService.updateOneById(entity, entityId),
                this.modelMapper,
                Categories.class,
                categoriesDto,
                id,
                errors
        );
    }
    @RequestMapping(value = "/categories/hard/{id}", method = RequestMethod.DELETE)
    public void hardDeleteOneById(@PathVariable("id") Long id) {
        this.categoriesService.hardDeleteOneById(id);
    }
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public List<Categories> findAll() {
        List<Categories> all = this.categoriesService.findAll();
        return all;
    }
    @RequestMapping(value = "/categories/soft/{id}", method = RequestMethod.DELETE)
    public void softDeleteOneById(@PathVariable("id") Long id) {
        this.categoriesService.softDeleteOneById(id);
    }
}
