package com.sg.labs.pokerapp.model;

import com.sg.labs.pokerapp.enums.CardSuitEnum;
import com.sg.labs.pokerapp.enums.CardValueEnum;
import com.sg.labs.pokerapp.enums.HandOrderEnum;
import com.sg.labs.pokerapp.exceptions.PokerBusinessException;
import com.sg.labs.pokerapp.exceptions.PokerErrorConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor
public class Hand {

    /** 5 Cards contained in Hand **/
    private List<Card> cards;

    private Hand(DomainBuilder builder) {
        this.cards = builder.cards;
    }

    public static DomainBuilder domainBuilder() {
        return new DomainBuilder();
    }

    public static class DomainBuilder{
        List<Card> cards = new ArrayList<>();

        public DomainBuilder cards(List<Card> cards) {
            this.cards = cards;
            return this;
        }

        public DomainBuilder putCard(Card card) {
            this.cards.add(card);
            return this;
        }

        public Hand build() {
            Hand hand = new Hand(this);
            // apply invariant checker
            InvariantChecker.totalCardsNotEqualsFive.test(hand);
            return hand;
        }
    }

    /**
     * Check if the Hand don't contains duplication of card value.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isHighCard() {
        Set<CardValueEnum> result = findByFrequency(this.cards.stream().map(Card::getCardValue)
                .collect(Collectors.toList()), 1);

        return Optional.ofNullable(result).map(Set::size).orElse(0) == 5 ;
    }

    /**
     * Check if the Hand contains one Pair. Two cards in the hand have the same value.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isPair() {
        Set<CardValueEnum> result = findByFrequency(this.cards.stream().map(Card::getCardValue)
                .collect(Collectors.toList()), 2);

        return Optional.ofNullable(result).map(Set::size).orElse(0) == 1 ;
    }

    /**
     * Check if the Hand contains two Pairs.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isTwoPairs() {
        Set<CardValueEnum> result = findByFrequency(this.cards.stream().map(Card::getCardValue)
                .collect(Collectors.toList()), 2);

        return Optional.ofNullable(result).map(Set::size).orElse(0) == 2 ;
    }

    /**
     * Check if the Hand is Three of a kind. three of cards have the same value.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isThreeOfKind() {
        Set<CardValueEnum> result = findByFrequency(this.cards.stream().map(Card::getCardValue)
                .collect(Collectors.toList()), 3);

        return Optional.ofNullable(result).map(Set::size).orElse(0) == 1 ;
    }

    /**
     * Check if the Hand contains 5 cards with consecutive values.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isStraight() {
        List<Integer> list = this.cards.stream().map(Card::getCardValue).map(cardValueEnum -> cardValueEnum.ordinal())
                .collect(Collectors.toList());

        return isConsecutive(new ArrayList<>(list));
    }

    /**
     * Check if the Hand contains 5 cards of the same suit.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isFlush() {
        List<CardSuitEnum> list = this.cards.stream().map(Card::getCardSuit)
                .collect(Collectors.toList());

        return list.stream().distinct().count() == 1;
    }

    /**
     * Check if the Hand contains 3 cards of the same value, with the remaining 2 cards forming a pair.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isFullHouse() {
        return isPair() && isThreeOfKind();
    }

    /**
     * Check if the Hand contains 4 cards of the same value.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isFourOfAKinf() {
        Set<CardValueEnum> result = findByFrequency(this.cards.stream().map(Card::getCardValue)
                .collect(Collectors.toList()), 4);

        return Optional.ofNullable(result).map(Set::size).orElse(0) == 1 ;
    }

    /**
     * Check if the Hand contains 5 cards of the same suit with consecutive values.
     * @return boolean true if condition respected, orElse false
     */
    public Boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    /**
     * Get Hand Order : The ordinal of enum is used to order the hand rank
     * @return HandOrderEnum
     */
    public HandOrderEnum getHandOrder() {
        if(isStraightFlush()) {
            return HandOrderEnum.STRAIGHT_FLUSH;
        } else if(isFourOfAKinf()) {
            return HandOrderEnum.FOUR_OF_A_KIND;
        } else if(isFullHouse()) {
            return HandOrderEnum.FULL_HOUSE;
        } else if(isFlush()) {
          return HandOrderEnum.FLUSH;
        } else if(isStraight()) {
            return HandOrderEnum.STRAIGHT;
        } else if(isThreeOfKind()) {
            return HandOrderEnum.THREE_OF_KIND;
        } else if(isTwoPairs()) {
            return HandOrderEnum.TWO_PAIRS;
        } else if(isPair()) {
            return HandOrderEnum.PAIR;
        }
        return HandOrderEnum.HIGH_CARD;
    }

    public static <T> Set<T> findByFrequency(List<T> list, int frequency) {

        return list.stream().filter(i -> Collections.frequency(list, i) == frequency)
                .collect(Collectors.toSet());

    }

    public boolean isConsecutive(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) + 1 != list.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static class InvariantChecker {
        final static private Predicate<Hand> totalCardsNotEqualsFive = req -> {
            if (req.cards.size() != 5) {
                throw new PokerBusinessException(new PokerBusinessException.Builder(PokerErrorConstants.ERR_HAND_TOTAL_CARD_NOT_EQUALS_FIVE));
            }

            return true;
        };
    }
}
