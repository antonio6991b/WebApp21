package ru.bolgov.soulbeer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.entity.ShopSellerLink;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopSellersLinkRepository extends CrudRepository<ShopSellerLink, Long> {

    @Query("from ShopSellerLink s where s.shopId = ?1")
    List<ShopSellerLink> findByShopId(Long shopId);

    @Query("from ShopSellerLink s where s.sellerId = ?1")
    List<ShopSellerLink> findBySellerId(Long sellerId);

    @Query("from ShopSellerLink s where s.sellerId = ?1 and s.shopId = ?2")
    List<Optional<ShopSellerLink>> countLink(Long sellerId, Long shopId);


}
