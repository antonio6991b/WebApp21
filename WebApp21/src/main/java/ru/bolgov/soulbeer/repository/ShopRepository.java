package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.ProductCategory;
import ru.bolgov.soulbeer.model.entity.Shop;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
    List<Shop> findAll();
    Optional<Shop> findById(Long id);

    @Query("from Shop s where s.shopName = ?1")
    Shop findByName(String name);
}
