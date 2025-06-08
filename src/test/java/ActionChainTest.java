

import com.example.task14.ActionChain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ActionChainTest {

    @Test
    void testDrawReturnsValidType() {
        ActionChain chain = new ActionChain();
        for (int i = 0; i < 100; i++) {
            int type = chain.draw();
            assertTrue(type == ActionChain.WIN || type == ActionChain.LOSE || type == ActionChain.SECOND_CHANCE);
        }
    }
}