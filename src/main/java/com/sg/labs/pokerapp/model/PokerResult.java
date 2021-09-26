package com.sg.labs.pokerapp.model;

import com.sg.labs.pokerapp.enums.CardValueEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class PokerResult {

    private Hand winner;
    private CardValueEnum cardValueOfWin;

}
