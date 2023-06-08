package ecommerce.v1.models.dto.suppliers.create.valid.validators;

import ecommerce.v1.models.entities.Categories;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ecommerce.v1.models.dto.suppliers.create.CategoriesDto;
import ecommerce.v1.repositories.CategoriesRepository;

import java.util.Optional;

public class CategoriesFound implements ConstraintValidator<
        ecommerce.v1.models.dto.suppliers.create.valid.annotations.CategoriesFound,
        CategoriesDto> {
    private CategoriesRepository categoriesRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isValid(CategoriesDto categoriesDto, ConstraintValidatorContext constraintValidatorContext) {
        if (categoriesDto == null) return true;
        if (categoriesDto.getId() == null) return false;
        Optional<Categories> byId = this.categoriesRepository.findById(categoriesDto.getId());
        if (byId.isPresent()) {
            Categories map = this.modelMapper.map(categoriesDto, Categories.class);
            if (map.equals(byId.get())) return true;
        } else if (byId.isEmpty()) {
            return false;
        }
        return false;
    }
}
