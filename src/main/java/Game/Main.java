package Game;

import Cards.InterfacesGroundclass.Card;
import Cards.InterfacesGroundclass.WishCard;
import Connector.*;
import Players.HumanPlayer;
import Players.Player;
import Players.RandomBot;
import Repository.CardDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        /*List<Player> players = new ArrayList<>();
        Connector connector = new BaseConnector();
        players.add(new RandomBot("Player 0", connector));
        players.add(new RandomBot("Player 1", connector));
        players.add(new RandomBot("Player 2", connector));
        players.add(new HumanPlayer("Human 3", connector));
        connector.startGame(players);*/
        Set<Card> wishcards = new HashSet<>();
        List<Card> allCards = CardDatabase.ALL_CARDS_ONCE_IMMUTABLE;
        for (Card card : allCards) {
            if (card instanceof WishCard) wishcards.add(card);
        }
        System.out.println("Breakpoint");
    }
}