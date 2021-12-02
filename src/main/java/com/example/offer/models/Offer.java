package com.example.offer.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;

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

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "offer_id"))
    private List<Long> paidTypesId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"))
    private Set<Characteristic> characteristics;

    public void addCharacteristic(Characteristic characteristic) {
        characteristics.add(characteristic);
    }

    public void deleteCharacteristic() {
        characteristics.forEach(characteristic -> characteristic.getOffers().remove(this));
    }
}
