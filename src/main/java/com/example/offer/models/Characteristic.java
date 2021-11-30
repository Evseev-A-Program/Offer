package com.example.offer.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
}
