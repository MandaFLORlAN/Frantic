package CardsTest;

import Cards.ColloredSpecial.*;
import Cards.InterfacesGroundclass.Card;
import Cards.NormalAndCurses.BlackCard;
import Cards.NormalAndCurses.FuckYou;
import Cards.NormalAndCurses.RegularCard;
import Cards.Wishcards.*;
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
    public void blackTest() {
        assertEquals(new BlackCard(3), Card.fromString("Black: 3"));
        assertEquals(new BlackCard(7), Card.fromString("Black: 7"));
    }

    @Test
    public void exchangeTest() {
        assertEquals(new Exchange(Color.GREEN), Card.fromString("Exchange: GREEN"));
        assertEquals(new Exchange(Color.RED), Card.fromString("Exchange: RED"));
    }

    @Test
    public void giftTest() {
        assertEquals(new Gift(Color.GREEN), Card.fromString("Gift: GREEN"));
        assertEquals(new Gift(Color.BLUE), Card.fromString("Gift: BLUE"));
    }

    @Test
    public void secondChanceTest() {
        assertEquals(new SecondChance(Color.YELLOW), Card.fromString("2nd Chance: YELLOW"));
        assertEquals(new SecondChance(Color.BLUE), Card.fromString("2nd Chance: BLUE"));
    }

    @Test
    public void skipTest() {
        assertEquals(new Skip(Color.PURPLE), Card.fromString("Skip: PURPLE"));
        assertEquals(new Skip(Color.BLUE), Card.fromString("Skip: BLUE"));
    }

    @Test
    public void TROUBLEMAKERTest() {
        assertEquals(new TROUBLEMAKER(Color.PURPLE), Card.fromString("TROUBLEMAKER: PURPLE"));
        assertEquals(new TROUBLEMAKER(Color.BLUE), Card.fromString("TROUBLEMAKER: BLUE"));
    }

    @Test
    public void fuckYouTest() {
        assertEquals(new FuckYou(), Card.fromString("Fuck You"));
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

    @Test
    public void wishcardTest() {
        assertEquals(new Counterattack(), Card.fromString("Counterattack: GREEN"));
        assertEquals(new Counterattack(), Card.fromString("Counterattack: RED"));
        assertEquals(new Equality(), Card.fromString("Equality: BLUE"));
        assertEquals(new Equality(), Card.fromString("Equality: YELLOW"));
        assertEquals(new NiceTry(), Card.fromString("Nice Try: PURPLE"));
        assertEquals(new NiceTry(), Card.fromString("Nice Try: YELLOW"));
    }

}
