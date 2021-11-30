package com.example.offer.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "offer_id"))
    private List<Long> paidTypesId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Characteristic> characteristicList;

}
