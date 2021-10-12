package ru.bolgov.soulbeer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.Product;
import ru.bolgov.soulbeer.model.ProductCategory;
import ru.bolgov.soulbeer.model.Seller;

import java.util.List;
import java.util.Objects;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

    @Query("from Product p where p.productCategory.categoryId = ?1")
    List<Product> findByCategoryId(Long categoryId);

    @Query("from Product p where p.productName = ?1")
    Product findByName(String name);

    default void edit(Product product, Long id){
        Product tmp = this.findById(id).orElse(null);
        if(Objects.nonNull(tmp)){
            tmp.setProductName(product.getProductName());
            tmp.setProductCategory(product.getProductCategory());
            tmp.setProductMaker(product.getProductMaker());
            tmp.setProductOrder(product.getProductOrder());
            this.save(tmp);
        }
    }
}
