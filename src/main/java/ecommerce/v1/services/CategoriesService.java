package ecommerce.v1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecommerce.v1.models.entities.Categories;
import ecommerce.v1.repositories.CategoriesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    private CategoriesRepository categoriesRepository;
    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }
    public Categories create(Categories categories) {
        Categories saveCategories = this.categoriesRepository.save(categories);
        return saveCategories;
    }
    public Categories updateOneById(Categories categories, Long id) {
        Optional<Categories> byId = this.categoriesRepository.findById(id);
        if (byId.isPresent()) {
            Categories categoriesUpdate = byId.get();
            categoriesUpdate.setName(categories.getName());
            Categories saveCategories = this.categoriesRepository.save(categoriesUpdate);
            return saveCategories;
        } else {
            return null;
        }
    }
    public void hardDeleteOneById(Long id) {
        Optional<Categories> byId = this.categoriesRepository.findById(id);
        if (id != null && byId.isPresent()) {
            this.categoriesRepository.deleteById(id);
        }
    }
    public List<Categories> findAll() {
        List<Categories> all = this.categoriesRepository.findAll();
        return all;
    }
    public void softDeleteOneById(Long id) {
        Optional<Categories> byId = this.categoriesRepository.findById(id);
        if (byId.isPresent()) {
            Categories categories = byId.get();
            categories.setHidden(true);
            this.categoriesRepository.save(categories);
        }
    }
}
