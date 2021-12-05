package com.example.offer.repository;

import com.example.offer.models.Offer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferDao extends CrudRepository<Offer, Long> {

    @Modifying
   @Query (nativeQuery = true, value = "select offer_id from offer_paid_types_id where paid_types_id = :id")
    List<Long> checkPaidTypebyId(@Param("id") Long id);
}

