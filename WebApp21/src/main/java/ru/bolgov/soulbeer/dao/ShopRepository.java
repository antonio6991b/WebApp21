package ru.bolgov.soulbeer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.Shop;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {
    List<Shop> findAll();
    Optional<Shop> findById(Long id);

}
