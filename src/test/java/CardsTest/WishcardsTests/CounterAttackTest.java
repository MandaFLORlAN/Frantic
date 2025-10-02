package CardsTest.WishcardsTests;


import Cards.Wishcards.Counterattack;
import Connector.TestConnector;
import Enums.Color;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CounterAttackTest {
    @Test
    public void constructorTest() {
        assertNull(new Counterattack().getColor());
        assertEquals("Counterattack", new Counterattack().getName());
        assertEquals(0, new Counterattack().getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");

        hasAll.play(new Counterattack());

        assertEquals(Color.BLUE, connector.getGame().getGameState().getPlayableColor());
        assertFalse(hasAll.getCards().contains(new Counterattack()));

        hasAll.addCard("Counterattack");
        assertTrue(hasAll.getCards().contains(new Counterattack()));
        assertTrue(hasAll.wantToBlock("Gift"));
        assertFalse(hasAll.getCards().contains(new Counterattack()));
        assertFalse(hasAll.wantToBlock("Gift"));
    }
}
