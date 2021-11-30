package com.example.offer.repository;

import com.example.offer.models.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDao extends CrudRepository<Offer, Long> {
}

