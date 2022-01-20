package com.example.offer.transfer.OfferDTO;

import com.example.offer.models.Characteristic;
import com.example.offer.models.Offer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Builder
@Getter
@Setter
public class CharacteristicDTO {

    private Long id;

    private String name;

    private String description;

    public static CharacteristicDTO from(Characteristic characteristic) {
        return CharacteristicDTO.builder()
                .id(characteristic.getId())
                .name(characteristic.getName())
                .description(characteristic.getDescription())
                .build();
    }
}
