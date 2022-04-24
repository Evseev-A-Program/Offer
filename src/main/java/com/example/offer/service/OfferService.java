package com.example.offer.service;

import com.example.offer.exceptions.OfferNotFoundException;
import com.example.offer.exceptions.OfferPaidTypeNotFoundException;
import com.example.offer.models.Characteristic;
import com.example.offer.models.Offer;
import com.example.offer.repository.CategoryDao;
import com.example.offer.repository.CharacteristicDao;
import com.example.offer.repository.OfferDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferService {

    private final OfferDao offerDao;
    private final CategoryDao categoryDao;
    private final CharacteristicDao characteristicDao;


    public Offer findOfferById(Long id) throws OfferNotFoundException{
        Offer offer = offerDao.findById(id)
                .orElseThrow(() ->
                        new OfferNotFoundException ("Offer Not Found"));
        return offer;
}

    public List<Offer> findAllOffers(){
//        List<OfferDTO> listDTO = new ArrayList<>();
//        List<Offer> list = (List<Offer>) offerDao.findAll();
//        for (Offer offer : list) {
//            listDTO.add(OfferDTO.from(offer));
//        }
        return (List<Offer>) offerDao.findAll();
    }

    public List<Offer> findAllOffersNotNull(){
        List<Offer> list = (List<Offer>) offerDao.findAll();

        return list.stream()
                .filter(x -> x.getPaidTypesId()!=null&&
                                x.getCategory()!=null&&
                                x.getCharacteristic()!=null)
                                .collect(Collectors.toList());
    }

    public void deleteOfferById(Long id) throws OfferNotFoundException {
        Offer offer = offerDao.findById(id).get();
        if ( offer == null) {
            throw new OfferNotFoundException("Offer Not Found");
        }
        offerDao.deleteById(id);
    }

    public void saveOffer(Offer offer, Long categoryId,
                          String characteristicName,
                          String characteristicDescription) throws OfferPaidTypeNotFoundException {
        offer.setCategory(categoryDao.findById(categoryId).get());
        Characteristic characteristic = Characteristic.builder()
                .name(characteristicName)
                .description(characteristicDescription)
                .build();
        characteristicDao.save(characteristic);
        offer.setCharacteristic(characteristic);
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

    public void addPaidType(Long id, Long paidTypeId) {
        Offer offer = offerDao.findById(id).get();
        offer.getPaidTypesId().add(paidTypeId);
        offerDao.save(offer);
    }

    public void delPaidType(Long id, Long paidTypeId) {
        Offer offer = offerDao.findById(id).get();
        offer.getPaidTypesId().remove(paidTypeId);
        offerDao.save(offer);
    }

    public void addCategory(Long id, Long categoryId) {
        Offer offer = offerDao.findById(id).get();
        offer.setCategory(categoryDao.findById(categoryId).get());
        offerDao.save(offer);
    }

    public void addCharacteristic(Long id, String characteristicName, String characteristicDescription) {
        Offer offer = offerDao.findById(id).get();
        offer.setCharacteristic(Characteristic.builder()
                .name(characteristicName)
                .description(characteristicDescription)
                .build());
        offerDao.save(offer);
    }
}
