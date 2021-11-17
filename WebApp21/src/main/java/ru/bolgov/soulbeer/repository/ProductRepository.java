package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

    @Query("from Product p where p.productName = ?1")
    Product findByNameIgnoreCase(String name);

    @Query("select count(p) from Product p where p.categoryId = ?1 ")
    Long countByCategoryId(Long shopId);

    @Query("from Product p where p.categoryId = ?1")
    List<Product> findByCategoryId(Long categoryId);

    @Query("from Product p where p.productName = ?1 and p.categoryId = ?2")
    List<Product> findByProductNameAndCategoryId(String productName, long CategoryId);

    @Query("select count(p) from Product p where p.makerId = ?1 ")
    Long countByMakerId(Long makerId);

    @Query("from Product p where p.makerId = ?1")
    List<Product> findByMakerId(Long makerId);
}
