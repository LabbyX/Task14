package com.example.task14;

import java.util.Random;

public class ActionChain {
    private Handler chain;
    private Random random = new Random();

    public static final int WIN = 1;
    public static final int LOSE = 2;
    public static final int SECOND_CHANCE = 3;

    public ActionChain() {
        buildChain();
    }

    private void buildChain() {
        chain = new LoseHandler(new SecondChanceHandler(new WinHandler(null)));
    }

    public boolean process(int requestType, Player player) {
        return chain.process(requestType, player);
    }

    public int draw() {
        // Можно сделать более сложную логику, пока просто случайно
        return 1 + random.nextInt(3); // 1, 2 или 3
    }
}