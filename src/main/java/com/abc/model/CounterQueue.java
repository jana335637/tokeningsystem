package com.abc.model;

import java.util.LinkedList;
import java.util.Queue;

public class CounterQueue {
    private Queue<Token> tokensQueue = new LinkedList<>();

    public Queue<Token> getTokensQueue() {
        return tokensQueue;
    }

    public void setTokensQueue(Queue<Token> tokensQueue) {
        this.tokensQueue = tokensQueue;
    }
}
