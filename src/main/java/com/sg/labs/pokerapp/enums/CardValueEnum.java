package com.sg.labs.pokerapp.enums;

public enum CardValueEnum {

    TWO("2", "Two"), // 0
    THREE("3", "Three"), // 1
    FOUR("4", "Four"), // 2
    FIVE("5", "Five"), // 3
    SIX("6", "Six"),// 4
    SEVEN("7", "Seven"),// 5
    EIGHT("8", "Eight"),
    NINE("9", "Nine"),
    TEN("10", "Ten"),
    JAKC("J", "Jack"),
    QUEEN("Q", "Queen"),
    KING("K", "King"),
    ACE("A", "Ace");

    private String code;
    private String label;

    CardValueEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
