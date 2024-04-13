package com.internationaltransactions.model;

import com.internationaltransactions.model.enums.ShippingStatus;
import com.internationaltransactions.model.enums.ShippingType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Shipping implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String country;
    private String dateAndTimeSending;
    private String dateAndTimeArrival;

    @Enumerated(EnumType.STRING)
    private ShippingType type;
    @Enumerated(EnumType.STRING)
    private ShippingStatus status = ShippingStatus.NOT_COMPLETED;

    @ManyToOne
    private Product product;
    @ManyToOne
    private AppUser owner;
    @OneToOne(cascade = CascadeType.ALL)
    private Transaction transaction;

    public Shipping(String country, String dateAndTimeSending, String dateAndTimeArrival, ShippingType type, Product product, AppUser owner) {
        this.country = country;
        this.dateAndTimeSending = dateAndTimeSending;
        this.dateAndTimeArrival = dateAndTimeArrival;
        this.type = type;
        this.product = product;
        this.owner = owner;
        this.transaction = new Transaction(this);
    }
}
