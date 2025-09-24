package CardsTest;

import Cards.InterfacesGroundclass.Card;
import Cards.Wishcards.Fantastic;
import Cards.Wishcards.FantasticFour;
import Cards.NormalAndCurses.RegularCard;
import Enums.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EqualsTest {
    @Test
    public void testEquals() {
        assertEquals(new RegularCard(2, Color.BLUE), new RegularCard(2, Color.BLUE));
        assertNotEquals(new RegularCard(7, Color.RED), new RegularCard(8, Color.RED));
        assertNotEquals(new RegularCard(7, Color.RED), new RegularCard(7, Color.GREEN));
        assertEquals(new RegularCard(8, Color.YELLOW), new RegularCard(8, Color.YELLOW));
        assertEquals(new Fantastic(), Card.fromString("Fantastic: 4"));
        assertEquals(new Fantastic(), Card.fromString("Fantastic: BLUE"));
        assertEquals(new FantasticFour(), Card.fromString("FantasticFour: GREEN"));
    }
}
