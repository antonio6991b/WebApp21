package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.Seller;

import java.util.List;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {
    List<Seller> findAll();

    @Query("from Seller s where s.shopId = ?1")
    List<Seller> findByShopId(Long shopId);

    @Query("select count(s) from Seller s where s.shopId = ?1 ")
    Long countByShopId(Long shopId);

}
