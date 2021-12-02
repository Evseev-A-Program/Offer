package com.example.offer.repository;

import com.example.offer.models.Characteristic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicDao extends CrudRepository<Characteristic, Long> {

    Characteristic findByName(String name);
}
