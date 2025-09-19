package CardsTest;

import Cards.InterfacesGroundclass.Card;
import Cards.Wishcards.Fantastic;
import Game.GameState;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FantesticTest {
    GameState redFive = TestCaseProvider.getRedFive();
    GameState greenEight = TestCaseProvider.getGreenEight();
    GameState nullCase = TestCaseProvider.getNull();
    GameState zeroCase = TestCaseProvider.getZero();

    @Test
    void testConstructor() {
        Card testCard = new Fantastic();
        assertNull(testCard.getColor());
        assertEquals(0, testCard.getNumber());
        assertEquals("Fantastic", testCard.getName());
    }

    @Test
    void testIsPlayable () {
        Card testCard = new Fantastic();
        assertTrue(testCard.isPlayable(redFive));
        assertTrue(testCard.isPlayable(greenEight));
        assertTrue(testCard.isPlayable(nullCase));
        assertTrue(testCard.isPlayable(zeroCase));
    }
}
