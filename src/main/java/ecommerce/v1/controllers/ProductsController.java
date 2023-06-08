package ecommerce.v1.controllers;
import ecommerce.v1.configurations.components.others.Response;
import ecommerce.v1.models.dto.products.create.ProductsDto;
import ecommerce.v1.models.dto.searching.Search;
import ecommerce.v1.models.entities.Products;
import ecommerce.v1.models.entities.Suppliers;
import ecommerce.v1.services.ProductsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductsController {
    private ProductsService productsService;
    private Response<Products, ProductsDto> responseCreate;
    private Response<Products, ecommerce.v1.models.dto.products.update.ProductsDto> responseUpdate;
    private ModelMapper modelMapper;
    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }
    @Autowired
    public void setResponseCreate(Response<Products, ProductsDto> responseCreate) {
        this.responseCreate = responseCreate;
    }
    @Autowired
    public void setResponseUpdate(Response<Products, ecommerce.v1.models.dto.products.update.ProductsDto> responseUpdate) {
        this.responseUpdate = responseUpdate;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid ProductsDto productsDto, Errors errors) {
        return this.responseCreate.create(
                (entity) -> this.productsService.create(entity),
                this.modelMapper,
                Products.class,
                productsDto,
                errors);
    }
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOneById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ecommerce.v1.models.dto.products.update.ProductsDto productsDto,
            Errors errors) {
        return this.responseUpdate.update(
                (entity, entityId) -> this.productsService.updateOneById(entity, entityId),
                this.modelMapper,
                Products.class,
                productsDto,
                id,
                errors);
    }
    @RequestMapping(value = "/products/name", method = RequestMethod.POST)
    public List<Products> findByName(@RequestBody Products products) {
        List<Products> byName = this.productsService.findByName(products);
        return byName;
    }
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Products> findAll() {
        List<Products> all = this.productsService.findAll();
        return all;
    }
    @RequestMapping(value = "/products/search", method = RequestMethod.POST)
    public List<Products> searchByName(@RequestBody Search search) {
        Products products = new Products();
        products.setName(search.getKey());
        List<Products> byName = this.productsService.searchByName(products);
        return byName;
    }
    @RequestMapping(value = "/products/supplier/{id}", method = RequestMethod.GET)
    public List<Products> findBySupplier(@PathVariable("id") Long id) {
        Suppliers suppliers = new Suppliers();
        suppliers.setId(id);
        Products products = new Products();
        products.getSuppliers().add(suppliers);
        List<Products> bySupplier = this.productsService.findBySupplier(products);
        return bySupplier;
    }
    @RequestMapping(value = "/products/{page}/{size}", method = RequestMethod.GET)
    public Iterable<Products> findAllAndPagingSorting(@PathVariable("page") int page, @PathVariable("size") int size) {
        Sort sorting = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sorting);
        Iterable<Products> allAndPagingSorting = this.productsService.findAllAndPagingSorting(pageable);
        return allAndPagingSorting;
    }
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void hardDeleteOneById(@PathVariable("id") Long id) {
        this.productsService.hardDeleteOneById(id);
    }
}
