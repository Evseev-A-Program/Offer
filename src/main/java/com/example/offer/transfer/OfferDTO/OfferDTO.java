package com.example.offer.transfer.OfferDTO;

import com.example.offer.models.Category;
import com.example.offer.models.Characteristic;
import com.example.offer.models.Offer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
public class OfferDTO {

    private Long id;

    private String name;

    private float price;

    private Set<Long> paidTypesId;

    private List<CharacteristicDTO> characteristics;

    private String categoryName;

    public static OfferDTO from(Offer offer) {
        return OfferDTO.builder()
                .id(offer.getId())
                .name(offer.getName())
                .price(offer.getPrice())
                .paidTypesId(offer.getPaidTypesId())
               // .characteristics()
                .categoryName(offer.getCategory().getName())
                .build();
    }
}

