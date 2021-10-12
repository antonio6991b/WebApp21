package ru.bolgov.soulbeer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.ProductCategory;
import ru.bolgov.soulbeer.model.Seller;

import java.util.List;
import java.util.Objects;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
    List<ProductCategory> findAll();

    default void edit(ProductCategory productCategory, Long id){
        ProductCategory tmp = this.findById(id).orElse(null);
        if(Objects.nonNull(tmp)){
            tmp.setCategoryName(productCategory.getCategoryName());
            this.save(tmp);
        }
    }

    @Query("from ProductCategory p where p.categoryName = ?1")
    ProductCategory findByName(String name);
}
