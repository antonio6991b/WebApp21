package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

    List<ProductCategory> findAll();

    @Query("from ProductCategory p where p.categoryName = ?1")
    ProductCategory findByName(String name);

    Optional<ProductCategory> findById(Long categoryId);

    boolean existsByCategoryName(String categoryName);


}
