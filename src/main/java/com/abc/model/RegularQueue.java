package com.abc.model;

import java.util.Queue;

public class RegularQueue {
    private static RegularQueue regularQueue;

    public static RegularQueue getInstance() {
        if(regularQueue==null)
            regularQueue = new RegularQueue();
        return regularQueue;
    }

    private RegularQueue() {
    }

}
