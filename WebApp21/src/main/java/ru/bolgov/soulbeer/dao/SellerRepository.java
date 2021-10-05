package ru.bolgov.soulbeer.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bolgov.soulbeer.model.Seller;
import ru.bolgov.soulbeer.model.Shop;

import java.util.List;
import java.util.Objects;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {
    List<Seller> findAll();

    @Query("from Seller s where s.shop.shopId = ?1")
    List<Seller> findByShopId(Long shopId);

    default void edit(Seller seller, Long id){
        Seller tmpSeller = this.findById(id).orElse(null);
        if(Objects.nonNull(tmpSeller)){
            tmpSeller.setSellerName(seller.getSellerName());
            tmpSeller.setShop(seller.getShop());
            tmpSeller.setSellerPhone(seller.getSellerPhone());
            this.save(tmpSeller);
        }
    }
}
