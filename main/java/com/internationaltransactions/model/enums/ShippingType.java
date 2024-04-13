package com.internationaltransactions.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShippingType {
    AIRPLANE("Самолет"),
    SHIP("Теплоход"),
    TRUCK("Грузовик"),
    TRAIN("Поезд"),
    ;

    private final String name;
}

