package com.example.offer.transfer.OfferDTO;

import com.example.offer.models.Category;
import com.example.offer.models.Characteristic;
import com.example.offer.models.Offer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
public class OfferDTO {

    private Long id;


    private String name;

    private float price;


   // private List<Long> paidTypesId;


   // private Set<Characteristic> characteristics;


    private String categoryName;

    public static OfferDTO from(Offer offer) {
        return OfferDTO.builder()
                .name(offer.getName())
                .price(offer.getPrice())
                .categoryName(offer.getCategory().getName())
                .build();
    }
}

