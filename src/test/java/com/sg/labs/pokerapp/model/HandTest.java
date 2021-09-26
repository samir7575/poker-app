package com.sg.labs.pokerapp.model;

import com.sg.labs.pokerapp.enums.CardSuitEnum;
import com.sg.labs.pokerapp.enums.CardValueEnum;
import com.sg.labs.pokerapp.enums.HandOrderEnum;
import com.sg.labs.pokerapp.exceptions.PokerBusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HandTest {

    @Test
    void testHand_Without_Five_Cards() {
        // Throws exception if Hand doesn't contain 5 cards
        // Less than 5 cards
        Assertions.assertThrows(PokerBusinessException.class, () -> {
            Hand.domainBuilder().cards(Arrays.asList(
                            Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.CLUB).build(),
                            Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                    )
            ).build();
        });

        // More than 5 cards
        Assertions.assertThrows(PokerBusinessException.class, () -> {
            Hand.domainBuilder().cards(Arrays.asList(
                            Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.CLUB).build(),
                            Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build(),
                            Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.HEART).build(),
                            Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.SPADE).build(),
                            Card.domainBuilder().cardValue(CardValueEnum.QUEEN).cardSuit(CardSuitEnum.HEART).build(),
                            Card.domainBuilder().cardValue(CardValueEnum.TEN).cardSuit(CardSuitEnum.SPADE).build()
                    )
            ).build();
        });
    }

    @Test
    void testHand_isHighCard() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result = hand.isHighCard();

        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.HIGH_CARD);

        // Result is not HighCard
        Hand hand2 = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result2 = hand2.isHighCard();
        Assertions.assertFalse(result2);

    }

    @Test
    void testHand_isPair() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result = hand.isPair();

        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.PAIR);

        // Result is not Pair
        Hand hand2 = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TEN).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result2 = hand2.isPair();
        Assertions.assertFalse(result2);

    }

    @Test
    void testHand_isTwoPairs() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result = hand.isTwoPairs();
        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.TWO_PAIRS);

        // Result is not TwoPairs
        Hand hand2 = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result2 = hand2.isTwoPairs();
        Assertions.assertFalse(result2);

    }

    @Test
    void testHand_isThreeOfKind() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result = hand.isThreeOfKind();

        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.THREE_OF_KIND);

        // Result is not Three of a kind
        Hand hand2 = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.EIGHT).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.ACE).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result2 = hand2.isThreeOfKind();
        Assertions.assertFalse(result2);
    }

    @Test
    void testHand_isStraight() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.SIX).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();
        Boolean result = hand.isStraight();

        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.STRAIGHT);

        // Result is not Straight
        Boolean result2 = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.SIX).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build().isStraight();

        Assertions.assertFalse(result2);
    }

    @Test
    void testHand_isFlush() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.SIX).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();

        Boolean result =  hand.isFlush();

        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.FLUSH);

        // Result is not Flush
        Hand hand2= Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.SIX).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build();

        Boolean result2 = hand2.isFlush();
        Assertions.assertFalse(result2);
        org.assertj.core.api.Assertions.assertThat(hand2.getHandOrder()).isNotEqualTo(HandOrderEnum.FLUSH);
    }

    @Test
    void testHand_isFullHouse() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.CLUB).build()
                )
        ).build();

        Boolean result = hand.isFullHouse();

        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.FULL_HOUSE);

        Boolean result2 = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.SIX).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build().isFullHouse();

        Assertions.assertFalse(result2);
    }

    @Test
    void testHand_isFourOfAKinf() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.CLUB).build()
                )
        ).build();
        Boolean result = hand.isFourOfAKinf();

        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.FOUR_OF_A_KIND);

        Boolean result2 = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.THREE).cardSuit(CardSuitEnum.HEART).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FOUR).cardSuit(CardSuitEnum.SPADE).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.FIVE).cardSuit(CardSuitEnum.CLUB).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.SIX).cardSuit(CardSuitEnum.HEART).build()
                )
        ).build().isFourOfAKinf();

        Assertions.assertFalse(result2);
    }

    @Test
    void testHand_isStraightFlush() {
        Hand hand = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TEN).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.JAKC).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.QUEEN).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.KING).cardSuit(CardSuitEnum.DIAMOND).build()
                )
        ).build();
        Boolean result = hand.isStraightFlush();

        Assertions.assertTrue(result);
        org.assertj.core.api.Assertions.assertThat(hand.getHandOrder()).isEqualTo(HandOrderEnum.STRAIGHT_FLUSH);

        Boolean result2 = Hand.domainBuilder().cards(Arrays.asList(
                        Card.domainBuilder().cardValue(CardValueEnum.NINE).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TEN).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.JAKC).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.QUEEN).cardSuit(CardSuitEnum.DIAMOND).build(),
                        Card.domainBuilder().cardValue(CardValueEnum.TWO).cardSuit(CardSuitEnum.DIAMOND).build()
                )
        ).build().isStraightFlush();

        Assertions.assertFalse(result2);
    }
}