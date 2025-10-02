package CardsTest.WishcardsTests;

import Cards.NormalAndCurses.BlackCard;
import Cards.NormalAndCurses.RegularCard;
import Cards.Wishcards.Counterattack;
import Cards.Wishcards.Fantastic;
import Cards.Wishcards.FantasticFour;
import Cards.Wishcards.NiceTry;
import Connector.TestConnector;
import Enums.Color;
import Players.TestHelpingBot;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NiceTryTest {
    @Test
    public void constructorTest() {
        assertNull(new NiceTry().getColor());
        assertEquals("Nice Try", new NiceTry().getName());
        assertEquals(0, new NiceTry().getNumber());
    }

    @Test
    public void testSpecialFunction() {
        TestConnector connector = TestCaseProvider.getTestGame();
        TestHelpingBot hasAll = (TestHelpingBot) connector.getPlayers().get("hasAll");
        TestHelpingBot threeCards = (TestHelpingBot) connector.getPlayers().get("threeCards");

        hasAll.play(new NiceTry());

        assertEquals(Color.BLUE, connector.getGame().getGameState().getPlayableColor());

        assertEquals(3, threeCards.getCards().size());
        threeCards.play(new RegularCard(7, Color.RED));
        connector.getGame().checkGameOver();
        assertEquals(2, threeCards.getCards().size());
        threeCards.play(new Fantastic());
        connector.getGame().checkGameOver();
        assertEquals(1, threeCards.getCards().size());
        threeCards.play(new Fantastic());
        connector.getGame().checkGameOver();
        assertEquals(3, threeCards.getCards().size());
    }
}
