package ecommerce.v1.models.dto.products.create.valid.validators;

import ecommerce.v1.models.entities.Suppliers;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ecommerce.v1.models.dto.products.create.SuppliersDto;
import ecommerce.v1.repositories.SuppliersRepository;

import java.util.Optional;

public class SuppliersFound implements ConstraintValidator<
        ecommerce.v1.models.dto.products.create.valid.annotations.SuppliersFound,
        SuppliersDto[]> {
    private SuppliersRepository suppliersRepository;
    private ModelMapper modelMapper;
    @Autowired
    public void setSuppliersRepository(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isValid(SuppliersDto[] suppliersDto, ConstraintValidatorContext constraintValidatorContext) {
        if (suppliersDto == null) return true;
        for (SuppliersDto dto : suppliersDto) {
            if (dto.getId() == null) return false;
            Optional<Suppliers> byId = this.suppliersRepository.findById(dto.getId());
            if (byId.isPresent()) {
                Suppliers suppliers = byId.get();
                if (suppliers.getId() == dto.getId()) {
                    Suppliers map = this.modelMapper.map(dto, Suppliers.class);
                     if(map.equals(suppliers) == false) return false;
                }
            } else if (byId.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
