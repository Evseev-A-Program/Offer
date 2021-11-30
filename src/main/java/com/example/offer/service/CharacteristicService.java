package com.example.offer.service;

import com.example.offer.exceptions.CharacteristicNotFoundException;
import com.example.offer.exceptions.OfferNotFoundException;
import com.example.offer.models.Characteristic;
import com.example.offer.models.Offer;
import com.example.offer.repository.CharacteristicDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacteristicService {

    private final CharacteristicDao characteristicDao;

    public Characteristic findCharacteristicById(Long id) throws CharacteristicNotFoundException {
        Characteristic characteristic = characteristicDao.findById(id).get();
        if (characteristic == null){
            throw new CharacteristicNotFoundException("Characteristic Not Found");
        }
        return characteristic;
    }

    public Iterable<Characteristic> findAllCharacteristics(){
        return characteristicDao.findAll();
    }

    public void deleteCharacteristicById(Long id) throws CharacteristicNotFoundException {
        Characteristic characteristic = characteristicDao.findById(id).get();
        if (characteristic == null){
            throw new CharacteristicNotFoundException("Characteristic Not Found");
        }
        characteristicDao.delete(characteristic);
    }

    public void saveCharacteristic(Characteristic characteristic, Long offerId) throws OfferNotFoundException {
        Offer offer = new OfferService().findOfferById(offerId);
        if (offer == null) {
            throw new OfferNotFoundException("Offer Not Found");
        }
        characteristic.setOffer(offer);
        characteristicDao.save(characteristic);
    }
}
