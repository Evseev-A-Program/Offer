package com.example.offer.controllers;

import com.example.offer.exceptions.OfferNotFoundException;
import com.example.offer.models.Offer;
import com.example.offer.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offer")
@AllArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/add")
    public ResponseEntity addOffer(@RequestBody Offer offer){
        try{
            offerService.saveOffer(offer);
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
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity getAllOffers(){
        try{
            return ResponseEntity.ok(offerService.findAllOffers());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
}