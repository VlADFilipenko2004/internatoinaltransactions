package com.internationaltransactions.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionStatus {
    COMPLETED("Выполнено"),
    WAITING("В ожидании"),
    ;

    private final String name;
}

