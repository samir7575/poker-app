package com.sg.labs.pokerapp.engine;

import com.sg.labs.pokerapp.enums.CardSuitEnum;
import com.sg.labs.pokerapp.enums.CardValueEnum;
import com.sg.labs.pokerapp.enums.HandOrderEnum;
import com.sg.labs.pokerapp.model.Card;
import com.sg.labs.pokerapp.model.Hand;
import com.sg.labs.pokerapp.model.PokerResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class RankCalculatorTest {

    @Test
    void test_getHigherRank_case_1() {
        Hand blackPlayer = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.DIAMOND).build()
                )
        ).build();

        Hand whitePlayer = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();

        PokerResult pokerResult = RankCalculator.domainBuilder().blackPlayer(blackPlayer).whitePlayer(whitePlayer)
                .build()
                .getHigherRank();
        Assertions.assertThat(pokerResult).isNotNull();
        Assertions.assertThat(pokerResult.getWinner()).isEqualTo(whitePlayer);
        Assertions.assertThat(pokerResult.getCardValueOfWin()).isEqualTo(CardValueEnum.ACE);
    }

    @Test
    void test_getHigherRank_case_2() {
        Hand blackPlayer = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();

        Hand whitePlayer = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.QUEEN).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.SPADE).build()
                )
        ).build();

        PokerResult pokerResult = RankCalculator.domainBuilder().blackPlayer(blackPlayer).whitePlayer(whitePlayer)
                .build()
                .getHigherRank();
        Assertions.assertThat(pokerResult).isNotNull();
        Assertions.assertThat(pokerResult.getWinner()).isEqualTo(blackPlayer);
        Assertions.assertThat(pokerResult.getWinner().getHandOrder()).isEqualTo(HandOrderEnum.FULL_HOUSE);
    }

    @Test
    void test_getHigherRank_case_3() {
        Hand blackPlayer = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.SIX).cardSuit(CardSuitEnum.DIAMOND).build()
                )
        ).build();

        Hand whitePlayer = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.SIX).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();

        PokerResult pokerResult = RankCalculator.domainBuilder().blackPlayer(blackPlayer).whitePlayer(whitePlayer)
                .build()
                .getHigherRank();
        Assertions.assertThat(pokerResult).isNotNull();
        Assertions.assertThat(pokerResult.getWinner()).isEqualTo(blackPlayer);
        Assertions.assertThat(pokerResult.getWinner().getHandOrder()).isEqualTo(HandOrderEnum.HIGH_CARD);
        Assertions.assertThat(pokerResult.getCardValueOfWin()).isEqualTo(CardValueEnum.NINE);
    }

    @Test
    void test_getHigherRank_case_4() {
        Hand blackPlayer = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.DIAMOND).build()
                )
        ).build();

        Hand whitePlayer = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();

        PokerResult pokerResult = RankCalculator.domainBuilder().blackPlayer(blackPlayer).whitePlayer(whitePlayer)
                .build()
                .getHigherRank();
        Assertions.assertThat(pokerResult).isNotNull();
        Assertions.assertThat(pokerResult.getWinner()).isNull();
    }
}