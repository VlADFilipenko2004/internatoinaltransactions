package com.internationaltransactions.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShippingStatus {
    NOT_COMPLETED("Не выполнено"),
    COMPLETED("Выполнено"),
    ;

    private final String name;
}

