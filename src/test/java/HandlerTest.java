

import com.example.task14.Handler;
import com.example.task14.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HandlerTest {

    static class TestWinHandler extends Handler {
        boolean called = false;
        public TestWinHandler(Handler next) { super(next); }
        @Override
        public boolean process(int requestType, Player player) {
            if (requestType == 1) {
                called = true;
                player.addWin();
                return true;
            }
            return super.process(requestType, player);
        }
    }

    static class TestLoseHandler extends Handler {
        boolean called = false;
        public TestLoseHandler(Handler next) { super(next); }
        @Override
        public boolean process(int requestType, Player player) {
            if (requestType == 2) {
                called = true;
                player.addLoss();
                return false;
            }
            return super.process(requestType, player);
        }
    }

    @Test
    void testChainProcessing() {
        Player player = new Player("Test", 5);
        TestLoseHandler lose = new TestLoseHandler(null);
        TestWinHandler win = new TestWinHandler(lose);

        // WIN
        boolean result = win.process(1, player);
        assertTrue(result);
        assertTrue(win.called);
        assertEquals(1, player.getWins());

        // LOSE
        result = win.process(2, player);
        assertFalse(result);
        assertTrue(lose.called);
        assertEquals(1, player.getLosses());
    }
}