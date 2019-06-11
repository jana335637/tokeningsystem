package com.abc.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class PremiumQueue<Token> extends LinkedList<Token> {
    private static PremiumQueue premiumQueue = new PremiumQueue();

    public static PremiumQueue getPremiumQueue() {
        return premiumQueue;
    }

    private PremiumQueue() {
    }

    @Override
    public Object clone() {
        try {
            throw new CloneNotSupportedException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Object readResolve() {
        return premiumQueue;
    }
}
