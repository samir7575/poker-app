package com.sg.labs.pokerapp.model;

import com.sg.labs.pokerapp.enums.CardSuitEnum;
import com.sg.labs.pokerapp.enums.CardValueEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Card {

    /** Pair of CardValue and CardCategory **/
    private CardValueEnum cardValue;
    private CardSuitEnum cardSuit;

    private Card(DomainBuilder builder) {
        this.cardValue = builder.cardValue;
        this.cardSuit = builder.cardCategory;
    }

    public static DomainBuilder domainBuilder() {
        return new DomainBuilder();
    }

    public static class DomainBuilder{
        private CardValueEnum cardValue;
        private CardSuitEnum cardCategory;

        public DomainBuilder cardValue(CardValueEnum cardValue) {
            this.cardValue = cardValue;
            return this;
        }

        public DomainBuilder cardSuit(CardSuitEnum cardCategory) {
            this.cardCategory = cardCategory;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }

}
