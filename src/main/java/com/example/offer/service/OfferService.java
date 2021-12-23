package com.example.offer.service;

import com.example.offer.exceptions.OfferNotFoundException;
import com.example.offer.exceptions.OfferPaidTypeNotFoundException;
import com.example.offer.models.Offer;
import com.example.offer.repository.OfferDao;
import com.example.offer.transfer.OfferDTO.OfferDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

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

    public List<OfferDTO> findAllOffers(){
        List<OfferDTO> listDTO = new ArrayList<>();
        List<Offer> list = (List<Offer>) offerDao.findAll();
        for (Offer offer : list) {
            listDTO.add(OfferDTO.from(offer));
        }
        return listDTO;
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

    public Boolean checkPaidType(Long id) {

        List<Long> listPT = offerDao.checkPaidTypebyId(id);
        if (listPT.isEmpty()) return true;
        return false;
    }
}
