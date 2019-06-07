package com.abc.util;

import com.abc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TokenStub {
    private static Map<Long, Token> tokens = new HashMap<>();
    private static Long idIndex = 0L;

    static List<CounterQueue> counters = new ArrayList<>();

    @Autowired
    static PremiumQueue premiumQueue;

    @Autowired
    static RegularQueue regularQueue;

    //populate initial tokens list
    static {

        CounterQueue c1 = new CounterQueue("c1", "Sophia");
        CounterQueue c2 = new CounterQueue("c2", "Surya");
        CounterQueue c3 = new CounterQueue("c3", "Sandy");

        counters.add(c1);
        counters.add(c2);
        counters.add(c3);
        mapQueues(counters);


    }

    private static void mapQueues(List<CounterQueue> counters) {
        List<Customer> customers = CustomerStub.list();

        PremiumQueue premiumQueue = PremiumQueue.getPremiumQueue();
        RegularQueue regularQueue = RegularQueue.getRegularQueue();
        for (Customer customer : customers) {
            tokens.put(++idIndex, new Token(idIndex, customer.getPriorityType(), Status.OPEN, customer.getCustId()));
            TokenUtils.assignTokenToQueue(tokens.get(idIndex),counters);
        }
    }

    public static List<CounterQueue> getQueues() {
        return counters;
    }
    public static Map<Long, Token> getTokens() {
        return tokens;
    }

    public static Token create(Token token) {
        token.setId(++idIndex);
        tokens.put(idIndex, token);
        return token;
    }

    public static Token get(Long id) {
        return tokens.get(id);
    }

    public static Token update(Long id, Token token) {
        tokens.put(id, token);
        return token;
    }

    public static Token delete(Long id) {
        return tokens.remove(id);
    }

}
