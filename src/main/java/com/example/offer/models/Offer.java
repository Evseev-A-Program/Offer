package com.example.offer.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private float price;

    private String image;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "offer_id"))
    private Set<Long> paidTypesId;

    @ManyToOne
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "characteristic_id")
    private Characteristic characteristic;


}
