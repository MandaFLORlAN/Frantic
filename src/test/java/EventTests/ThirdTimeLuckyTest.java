package EventTests;

import Connector.TestConnector;
import Events.Basegame.MatingSeason;
import Events.Basegame.ThirdTimeLucky;
import Players.Player;
import Repository.CardDatabase;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdTimeLuckyTest {
    @Test
    public void TestEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");

        connector.triggerEvent( "hasAll",new ThirdTimeLucky());

        assertEquals(6, threeCards.getCards().size());
        assertEquals(CardDatabase.UNIQUE_CARDS + 3, hasAll.getCards().size());
        assertEquals(8, fiveCards.getCards().size());
        assertEquals(13, tenCards.getCards().size());
    }
}
