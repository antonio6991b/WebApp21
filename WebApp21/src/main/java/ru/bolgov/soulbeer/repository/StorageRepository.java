package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.Storage;

import java.util.List;

@Repository
public interface StorageRepository extends CrudRepository<Storage, Long> {

    @Query("from Storage storage where storage.shopId = ?1")
    List<Storage> findByShopId(Long shopId);
}
