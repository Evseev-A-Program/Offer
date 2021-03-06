package com.example.offer.controllers;

import com.example.offer.exceptions.CharacteristicNotFoundException;
import com.example.offer.exceptions.OfferNotFoundException;
import com.example.offer.models.Characteristic;
import com.example.offer.service.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characteristic")
public class CharacteristicController {

    @Autowired
    private CharacteristicService characteristicService;

    @PostMapping("/add")
    public ResponseEntity addCharacteristic(@RequestBody Characteristic characteristic){
        try{
            characteristicService.saveCharacteristic(characteristic);
            return ResponseEntity.ok("Characteristic save");
        } catch (OfferNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getOneCharacteristic(@RequestParam Long id){
        try{
            return ResponseEntity.ok(characteristicService.findCharacteristicById(id));
        } catch (CharacteristicNotFoundException e){
            return ResponseEntity.badRequest().body((e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllCharacteristic(){
            return ResponseEntity.ok(characteristicService.findAllCharacteristics());
    }
}
