

import com.example.task14.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPayAndAddCoins() {
        Player player = new Player("Test", 5);
        assertTrue(player.pay(3));
        assertEquals(2, player.getCoins());
        assertFalse(player.pay(5));
        assertEquals(2, player.getCoins());
        player.addCoins(10);
        assertEquals(12, player.getCoins());
    }

    @Test
    void testWinsAndLosses() {
        Player player = new Player("Test", 1);
        assertEquals(0, player.getWins());
        assertEquals(0, player.getLosses());
        player.addWin();
        player.addLoss();
        assertEquals(1, player.getWins());
        assertEquals(1, player.getLosses());
    }
}