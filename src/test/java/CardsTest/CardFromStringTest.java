package CardsTest;

import Cards.ColloredSpecial.Gift;
import Cards.InterfacesGroundclass.Card;
import Cards.NormalAndCurses.RegularCard;
import Cards.Wishcards.Fantastic;
import Cards.Wishcards.FantasticFour;
import Enums.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardFromStringTest {

    @Test
    public void basiCardsTest() {
        assertEquals(new RegularCard(2, Color.BLUE), Card.fromString("BLUE: 2"));
        assertEquals(new RegularCard(5, Color.RED), Card.fromString("RED: 5"));
        assertEquals(new RegularCard(2, Color.YELLOW), Card.fromString("YELLOW: 2"));
        assertEquals(new RegularCard(1, Color.GREEN), Card.fromString("GREEN: 1"));
    }

    @Test
    public void giftTest() {
        assertEquals(new Gift(Color.GREEN), Card.fromString("Gift: GREEN"));
        assertEquals(new Gift(Color.BLUE), Card.fromString("Gift: BLUE"));
    }

    @Test
    public void fantasticTest() {
        assertEquals(new Fantastic(), Card.fromString("Fantastic: BLUE"));
        assertEquals(new Fantastic(), Card.fromString("Fantastic: PURPLE"));
        assertEquals(new Fantastic(), Card.fromString("Fantastic: 3"));
        assertEquals(new Fantastic(), Card.fromString("Fantastic: 1"));
        assertEquals(new FantasticFour(), Card.fromString("FantasticFour: BLUE"));
        assertEquals(new FantasticFour(), Card.fromString("FantasticFour: PURPLE"));
        assertEquals(new FantasticFour(), Card.fromString("FantasticFour: 3"));
        assertEquals(new FantasticFour(), Card.fromString("FantasticFour: 1"));
    }
}
