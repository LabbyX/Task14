package com.example.task14;

public abstract class Handler {
    private Handler next;

    public Handler(Handler next) {
        this.next = next;
    }

    public boolean process(int requestType, Player player) {
        if (next != null) {
            return next.process(requestType, player);
        }
        return true;
    }
}