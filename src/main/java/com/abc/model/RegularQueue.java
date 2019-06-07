package com.abc.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RegularQueue<Token> extends LinkedList<Token> {
    private static RegularQueue regularQueue = new RegularQueue();

    public static RegularQueue getRegularQueue() {
        return regularQueue;
    }

    private RegularQueue() {
    }

    protected Object readResolve() {
        return regularQueue;
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
}
