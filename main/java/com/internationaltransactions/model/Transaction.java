package com.internationaltransactions.model;

import com.internationaltransactions.model.enums.TransactionStatus;
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
public class Transaction implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String dateAndTime = "";
    private int price = 0;
    private String sending = "";
    private String arrival = "";

    @Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.WAITING;

    @OneToOne
    private Shipping shipping;

    public Transaction(Shipping shipping) {
        this.shipping = shipping;
    }
}
