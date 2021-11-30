package com.example.offer.service;

import com.example.offer.exceptions.OfferNotFoundException;
import com.example.offer.models.Offer;
import com.example.offer.repository.OfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private OfferDao offerDao;

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
        offerDao.deleteById(id);
    }

    public Offer saveOffer(Offer offer){
        return offerDao.save(offer);
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
