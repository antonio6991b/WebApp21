package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.bolgov.soulbeer.model.entity.Product;
import ru.bolgov.soulbeer.model.entity.ProductMaker;

import java.util.List;

public interface MakerRepository extends CrudRepository<ProductMaker, Long> {

    List<ProductMaker> findAll();

    @Query("from ProductMaker pm where pm.makerName = ?1")
    List<ProductMaker> findByName(String name);
}
