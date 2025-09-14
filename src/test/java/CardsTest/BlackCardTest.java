package CardsTest;

import Cards.BlackCard;
import Cards.Card;
import Game.GameState;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BlackCardTest {
    GameState redFive = TestCaseProvider.getRedFive();
    GameState greenEight = TestCaseProvider.getGreenEight();
    GameState nullCase = TestCaseProvider.getNull();
    GameState zeroCase = TestCaseProvider.getZero();

    @Test
    void testConstructor() {
        Card testCard = new BlackCard(8);
        assertNull(testCard.getColor());
        assertEquals(8, testCard.getNumber());
        assertEquals("Black: 8", testCard.getName());
    }

    @Test
    void testIsPlayable() {
        Card testCard = new BlackCard(8);
        assertFalse(testCard.isPlayable(redFive));
        assertFalse(testCard.isPlayable(nullCase));
        assertFalse(testCard.isPlayable(zeroCase));
        assertTrue(testCard.isPlayable(greenEight));
    }

}
