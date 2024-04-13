package com.internationaltransactions.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int weight;

    @ManyToOne
    private AppUser owner;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Shipping> shippings = new ArrayList<>();

    public Product(String name, int weight, AppUser owner) {
        this.name = name;
        this.weight = weight;
        this.owner = owner;
    }

    public void set(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
}
