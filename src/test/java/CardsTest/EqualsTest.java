package CardsTest;

import Cards.ColloredSpecial.*;
import Cards.InterfacesGroundclass.Card;
import Cards.NormalAndCurses.BlackCard;
import Cards.NormalAndCurses.FuckYou;
import Cards.Wishcards.*;
import Cards.NormalAndCurses.RegularCard;
import Enums.Color;
import com.sun.scenario.effect.impl.sw.RendererDelegate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EqualsTest {

    @Test
    public void exchangeEqualsTest() {
        assertEquals(new Exchange(Color.BLUE), new Exchange(Color.BLUE));
        assertEquals(new Exchange(Color.RED), new Exchange(Color.RED));
        assertNotEquals(new Exchange(Color.RED), new Exchange(Color.GREEN));
        assertNotEquals(new Exchange(Color.RED), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void giftEqualsTest() {
        assertEquals(new Gift(Color.BLUE), new Gift(Color.BLUE));
        assertEquals(new Gift(Color.RED), new Gift(Color.RED));
        assertNotEquals(new Gift(Color.RED), new Gift(Color.GREEN));
        assertNotEquals(new Gift(Color.RED), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void secondChanceEqualsTest() {
        assertEquals(new SecondChance(Color.BLUE), new SecondChance(Color.BLUE));
        assertEquals(new SecondChance(Color.YELLOW), new SecondChance(Color.YELLOW));
        assertEquals(new SecondChance(Color.PURPLE), new SecondChance(Color.PURPLE));
        assertNotEquals(new Gift(Color.RED), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void skipEqualsTest() {
        assertEquals(new Skip(Color.BLUE), new Skip(Color.BLUE));
        assertEquals(new Skip(Color.YELLOW), new Skip(Color.YELLOW));
        assertEquals(new Skip(Color.PURPLE), new Skip(Color.PURPLE));
        assertNotEquals(new Skip(Color.RED), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void TROUBLEMAKEREqualsTest() {
        assertEquals(new TROUBLEMAKER(Color.BLUE), new TROUBLEMAKER(Color.BLUE));
        assertEquals(new TROUBLEMAKER(Color.YELLOW), new TROUBLEMAKER(Color.YELLOW));
        assertEquals(new TROUBLEMAKER(Color.PURPLE), new TROUBLEMAKER(Color.PURPLE));
        assertNotEquals(new TROUBLEMAKER(Color.RED), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void blackEqualsTest() {
        assertEquals(new BlackCard(1), new BlackCard(1));
        assertEquals(new BlackCard(2), new BlackCard(2));
        assertEquals(new BlackCard(3), new BlackCard(3));
        assertNotEquals(new BlackCard(4), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void fuckYouEqualsTest() {
        assertEquals(new FuckYou(), new FuckYou());
        assertNotEquals(new FuckYou(), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void regularCardEqualsTest() {
        assertEquals(new RegularCard(1,Color.RED), new RegularCard(1,Color.RED));
        assertEquals(new RegularCard(3,Color.BLUE), new RegularCard(3,Color.BLUE));
        assertEquals(new RegularCard(2,Color.BLUE), new RegularCard(2,Color.BLUE));
        assertNotEquals(new RegularCard(1,Color.RED), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void counterAttackEqualsTest() {
        assertEquals(new Counterattack(), new Counterattack());
        assertNotEquals(new Counterattack(), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void equalityEqualsTest() {
        assertEquals(new Equality(), new Equality());
        assertNotEquals(new Equality(), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void fantasticEqualsTest() {
        assertEquals(new Fantastic(), new Fantastic());
        assertNotEquals(new Fantastic(), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void fantasticFourEqualsTest() {
        assertEquals(new FantasticFour(), new FantasticFour());
        assertNotEquals(new FantasticFour(), new RegularCard(1, Color.BLUE));
    }

    @Test
    public void niceTryEqualsTest() {
        assertEquals(new NiceTry(), new NiceTry());
        assertNotEquals(new NiceTry(), new RegularCard(1, Color.BLUE));
    }

}
