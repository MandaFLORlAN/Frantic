package Players;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;
import Game.GameState;

import java.util.ArrayList;
import java.util.List;

public class JavaFxPlayer implements Player {

    protected String playerName;
    protected List<Card> cards = new ArrayList<>();
    protected Connector connector;
    protected GameState gameState;


    @Override
    public void updateGamestate(GameState gameState) {

    }

    @Override
    public void updateGameActions(String Message) {

    }

    @Override
    public void addCard(Card card) {

    }

    @Override
    public void addCard(Card card, String message) {

    }

    @Override
    public void removeCard(Card cardName) {

    }

    @Override
    public void playMove() {

    }

    @Override
    public void clearCards() {

    }

    @Override
    public String getPlayerName() {
        return "";
    }

    @Override
    public List<Card> getCards() {
        return List.of();
    }

    @Override
    public boolean wantToUseEffect() {
        return false;
    }

    @Override
    public String fantasticWish() {
        return "";
    }

    @Override
    public String wishColor() {
        return "";
    }

    @Override
    public List<String> getTargets(String message, int numberOfTargets) {
        return List.of();
    }

    @Override
    public List<Card> getCardsToGiveAway(int numberOfCards) {
        return List.of();
    }

    @Override
    public Card drawRandomCard() {
        return null;
    }

    @Override
    public boolean wantToBlock(String attackCard) {
        return false;
    }

    @Override
    public List<Card> choseCards(List<Card> cards, int numberOfCards) {
        return List.of();
    }

}