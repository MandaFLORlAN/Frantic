package Players.LogicBots;

import Cards.InterfacesGroundclass.Card;
import Connector.Connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicBot1RandomGiveAways extends LogicBot1{
    public LogicBot1RandomGiveAways(String playerName, Connector connector) {
        super(playerName, connector);
    }

    @Override
    public List<String> getCardsToGiveAway(int numberOfCards) {
        List<String> cardsToGiveAway = new ArrayList<>();
        if (this.cards.size()<numberOfCards) numberOfCards = this.cards.size();
        for (int i = 0; i <  numberOfCards; i++) {
            cardsToGiveAway.add(this.cards.remove(new Random().nextInt(this.cards.size())).toString());
        }
        //cards will be taken in the transfer card method, they are removed to not be picked twice
        for (String card : cardsToGiveAway) {
            this.cards.add(Card.fromString(card));
        }
        return cardsToGiveAway;
    }


}
