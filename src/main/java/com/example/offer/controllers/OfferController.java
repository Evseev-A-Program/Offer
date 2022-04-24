package com.example.offer.controllers;

import com.example.offer.exceptions.OfferNotFoundException;
import com.example.offer.models.Offer;
import com.example.offer.service.OfferService;
import com.example.offer.transfer.OfferDTO.OfferDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/add")
    public ResponseEntity addOffer(@RequestBody Offer offer,
                            @RequestParam Long categoryId,
                                   String characteristicName,
                                   String characteristicDescription){
        try{
            offerService.saveOffer(offer,categoryId,characteristicName,characteristicDescription);
            return ResponseEntity.ok("Offer save");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/add-paid-type")
    public ResponseEntity addPaidType(@RequestParam Long id, Long paidTypeId){
        try{
            offerService.addPaidType(id, paidTypeId);
            return ResponseEntity.ok("Offer save");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/add-category")
    public ResponseEntity addCategory(@RequestParam Long id, Long categoryId){
        try{
            offerService.addCategory(id, categoryId);
            return ResponseEntity.ok("Offer save");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/add-characteristic")
    public ResponseEntity addCharacteristic(@RequestParam Long id,
                                            String characteristicName,
                                            String characteristicDescription){
        try{
            offerService.addCharacteristic(id, characteristicName, characteristicDescription);
            return ResponseEntity.ok("Offer save");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/del-paid-type")
    public ResponseEntity delPaidType(@RequestParam Long id, Long paidTypeId){
        try{
            offerService.delPaidType(id, paidTypeId);
            return ResponseEntity.ok("Offer save");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getOneOffer(@RequestParam Long id){
        try{
            return ResponseEntity.ok(offerService.findOfferById(id));
        } catch (OfferNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllOffers(){
        return ResponseEntity.ok(offerService.findAllOffers());
    }

    @GetMapping("/get/all-not-null")
    public ResponseEntity getAllOffersNotNull(){
        return ResponseEntity.ok(offerService.findAllOffersNotNull());
    }

    @PutMapping("/update")
    public ResponseEntity updateOffer(@RequestBody Offer offer){
        try{
            offerService.updateOfferById(offer);
            return ResponseEntity.ok("Offer update");
        } catch (OfferNotFoundException e){
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@PathVariable Long id){
        try{
            offerService.deleteOfferById(id);
            return ResponseEntity.ok("Offer delete");
        } catch (OfferNotFoundException e){
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-paid-type")
    public ResponseEntity checkPaidType(@RequestParam Long paidTypeId){
        try{
            return ResponseEntity.ok(offerService.checkPaidType(paidTypeId));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
