package com.sg.labs.pokerapp.enums;

public enum CardSuitEnum {

    CLUB("C", "Club"),
    DIAMOND("D", "Diamond"),
    HEART("H", "Heart"),
    SPADE("S", "Spade");

    private String code;
    private String label;

    CardSuitEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
