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



    public JavaFxPlayer(String playerName, Connector connector) {
        this.playerName = playerName;
        this.connector = connector;
    }

    @Override
    public void updateGamestate(String gameState) {
        this.gameState = GameState.fromString(gameState);
    }

    @Override
    public void updateGameActions(String Message) {

    }

    @Override
    public void addCard(String cardName) {

    }

    @Override
    public void addCard(String cardName, String message) {

    }

    @Override
    public void removeCard(String cardName) {

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
    public List<String> getCardsToGiveAway(int numberOfCards) {
        return List.of();
    }

    @Override
    public String drawRandomCard() {
        return "";
    }

    @Override
    public boolean wantToBlock(String attackCard) {
        return false;
    }

    @Override
    public List<String> choseCards(List<String> cards, int numberOfCards) {
        return List.of();
    }
}

/*
try{
Thread.sleep(500);
}catch(InterruptedException e){}
*/