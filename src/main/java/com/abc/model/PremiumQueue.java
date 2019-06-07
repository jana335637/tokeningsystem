package com.abc.model;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class PremiumQueue extends LinkedList
{
    private static PremiumQueue premiumQueue;

    public static PremiumQueue getInstance() {
        if(premiumQueue==null)
            premiumQueue = new PremiumQueue();
        return premiumQueue;
    }

    private PremiumQueue() {
    }
}
