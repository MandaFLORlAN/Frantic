package EventTests;

import Connector.TestConnector;
import Players.Player;
import TestHelper.TestCaseProvider;
import org.junit.jupiter.api.Test;

public class DoomsdayTest {

    @Test
    public void testEventExecution() {
        TestConnector connector = TestCaseProvider.getTestGame();
        Player threeCards = connector.getPlayers().get("threeCards");
        Player hasAll = connector.getPlayers().get("hasAll");
        Player fiveCards = connector.getPlayers().get("fiveCards");
        Player tenCards = connector.getPlayers().get("tenCards");
    }

}
