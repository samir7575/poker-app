package com.sg.labs.pokerapp.engine;

import com.sg.labs.pokerapp.enums.CardValueEnum;
import com.sg.labs.pokerapp.model.Card;
import com.sg.labs.pokerapp.model.Hand;
import com.sg.labs.pokerapp.model.PokerResult;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rank Calculator
 */
@Getter
@ToString
public class RankCalculator {

    /** Hand of Player Black **/
    private Hand blackPlayer;

    /** Hand of Player White **/
    private Hand whitePlayer;

    private RankCalculator(DomainBuilder builder) {
        this.blackPlayer = builder.blackPlayer;
        this.whitePlayer = builder.whitePlayer;
    }

    public static DomainBuilder domainBuilder() {
        return new DomainBuilder();
    }

    public static class DomainBuilder {
        private Hand blackPlayer;
        private Hand whitePlayer;

        public DomainBuilder blackPlayer(Hand blackPlayer) {
            this.blackPlayer = blackPlayer;
            return this;
        }

        public DomainBuilder whitePlayer(Hand whitePlayer) {
            this.whitePlayer = whitePlayer;
            return this;
        }

        public RankCalculator build() {
            return new RankCalculator(this);
        }
    }

    public PokerResult getHigherRank() {
        Hand winner = null;
        CardValueEnum cardValueOfWin = null;
        if(this.blackPlayer.getHandOrder().ordinal() == this.whitePlayer.getHandOrder().ordinal()) {
            List<CardValueEnum> blackList = this.blackPlayer.getCards().stream().map(Card::getCardValue).collect(Collectors.toList());
            List<CardValueEnum> whiteList = this.whitePlayer.getCards().stream().map(Card::getCardValue).collect(Collectors.toList());

            List<CardValueEnum> differencesBlackList = blackList.stream()
                    .filter(element -> !whiteList.contains(element))
                    .collect(Collectors.toList());

            if(differencesBlackList.size() == 0) {
                // Tiebreak , no  winner
                winner = null;
            } else {
                List<CardValueEnum> differencesWhiteList = whiteList.stream()
                        .filter(element -> !blackList.contains(element))
                        .collect(Collectors.toList());

                CardValueEnum highestBlack = Collections.max(differencesBlackList.stream().collect(Collectors.toList()));
                CardValueEnum highestWhite = Collections.max(differencesWhiteList.stream().collect(Collectors.toList()));
                if(highestBlack.ordinal() == highestWhite.ordinal()) {
                    // Tiebreak , no  winner
                    winner = null;
                } else if(highestBlack.ordinal() > highestWhite.ordinal()){
                    winner = blackPlayer;
                    cardValueOfWin = highestBlack;
                } else if(highestBlack.ordinal() < highestWhite.ordinal()){
                    winner = whitePlayer;
                    cardValueOfWin = highestWhite;
                }
            }



        } else if(this.blackPlayer.getHandOrder().ordinal() > this.whitePlayer.getHandOrder().ordinal()) {
            winner = this.blackPlayer;
        } else if(this.blackPlayer.getHandOrder().ordinal() < this.whitePlayer.getHandOrder().ordinal()) {
            winner = this.whitePlayer;
        }

        return PokerResult.builder()
                .winner(winner)
                .cardValueOfWin(cardValueOfWin)
                .build();
    }

}
