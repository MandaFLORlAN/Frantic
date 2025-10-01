package CardsTest;

import Cards.InterfacesGroundclass.Card;
import Cards.NormalAndCurses.RegularCard;
import Enums.Color;
import TestHelper.TestCaseProvider;
import Game.GameState;

import org.junit.jupiter.api.Test;

import static Enums.Color.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegularCardTest {
    GameState redFive = TestCaseProvider.getRedFive();
    GameState nullCase = TestCaseProvider.getNull();
    GameState zeroCase = TestCaseProvider.getZero();

    @Test
    void testConstructor() {
        Card testCard = new RegularCard(8, Color.BLUE);
        assertEquals(BLUE, testCard.getColor());
        assertEquals(8, testCard.getNumber());
        assertEquals("BLUE: 8", testCard.getName());
        Card testCard2 = new RegularCard(4, PURPLE);
        assertEquals(PURPLE, testCard2.getColor());
        assertEquals(4, testCard2.getNumber());
        assertEquals("PURPLE: 4", testCard2.getName());
    }

    @Test
    void testIsPlayable() {
        Card testCard = new RegularCard(8, Color.BLUE);
        Card testCard2 = new RegularCard(6, GREEN);
        assertFalse(testCard.isPlayable(redFive,"Dummy"));
        assertFalse(testCard2.isPlayable(redFive,"Dummy"));
        assertFalse(testCard.isPlayable(nullCase,"Dummy"));
        assertTrue(testCard2.isPlayable(nullCase,"Dummy"));
        assertFalse(testCard.isPlayable(zeroCase,"Dummy"));
        assertFalse(testCard2.isPlayable(zeroCase,"Dummy"));
    }

}
