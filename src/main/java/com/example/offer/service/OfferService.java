package com.example.offer.service;

import com.example.offer.clients.CustomerClients;
import com.example.offer.exceptions.OfferPaidTypeNotFoundException;
import com.example.offer.exceptions.OfferNotFoundException;
import com.example.offer.models.Offer;
import com.example.offer.repository.OfferDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfferService {

    private final OfferDao offerDao;


    public Offer findOfferById(Long id) throws OfferNotFoundException {
    Offer offer = offerDao.findById(id).get();
        if ( offer == null) {
        throw new OfferNotFoundException("Offer Not Found");
    }
        return offer;
}

    public Iterable<Offer> findAllOffers(){
        return offerDao.findAll();
    }

    public void deleteOfferById(Long id) throws OfferNotFoundException {
        Offer offer = offerDao.findById(id).get();
        if ( offer == null) {
            throw new OfferNotFoundException("Offer Not Found");
        }
        offer.deleteCharacteristic();
        offerDao.deleteById(id);
    }

    public void saveOffer(Offer offer) throws OfferPaidTypeNotFoundException {

        if (offer.getPaidTypesId().isEmpty()){
                throw new OfferPaidTypeNotFoundException("Paid Type not found");
        }



        offerDao.save(offer);
    }

    public void updateOfferById(Offer offer) throws OfferNotFoundException {
        Offer newOffer = offerDao.findById(offer.getId()).get();
        if (newOffer == null) {
            throw new OfferNotFoundException("Offer Not Found");
        }
        newOffer.setName(offer.getName());
        newOffer.setPrice(offer.getPrice());
        offerDao.save(newOffer);
    }
}
