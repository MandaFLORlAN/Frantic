package Events;

import Cards.InterfacesGroundclass.Card;

import java.util.List;

public record CardsToGiveAway (List<Card> cards, String giver, String reciever) {
}
