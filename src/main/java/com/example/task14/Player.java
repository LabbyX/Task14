package com.example.task14;


public class Player {
    private String name;
    private int coins;
    private int wins;
    private int losses;

    public Player(String name, int coins) {
        this.name = name;
        this.coins = coins;
        this.wins = 0;
    }

    public boolean pay(int amount) {
        if (coins >= amount) {
            coins -= amount;
            return true;
        }
        return false;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public int getCoins() {
        return coins;
    }

    public void addWin() {
        wins++;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }
    public void addLoss() {
        losses++;
    }

    public int getLosses() {
        return losses;
    }

}