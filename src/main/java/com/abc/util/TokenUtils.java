package com.abc.util;

import com.abc.model.CounterQueue;
import com.abc.model.PremiumQueue;
import com.abc.model.RegularQueue;
import com.abc.model.Token;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class TokenUtils {

    public static boolean isCounterAvailable(List<CounterQueue> counters) {
        for (CounterQueue counter : counters) {
            if (counter.size() < 2) {
                return true;
            }
        }
        return false;
    }

    public static CounterQueue getAvailableQueue(List<CounterQueue> counters) {
        for (CounterQueue counter : counters) {
            if (counter.size() < 2) {
                return counter;
            }
        }
        return null;
    }

    public static boolean isValidTokenToServe(List<CounterQueue> counters, Token tokenToUpdate) {
        for (CounterQueue counter : counters) {
            if (counter.peek() != null) {
                Token token = (Token) counter.peek();
                if (token.getId().equals(tokenToUpdate.getId()))
                    return true;
            }
        }
        return false;
    }

    public static void assignTokenToQueue(Token token, List<CounterQueue> counters) {
        PremiumQueue premiumQueue = PremiumQueue.getPremiumQueue();
        RegularQueue regularQueue = RegularQueue.getRegularQueue();
        if (TokenUtils.isCounterAvailable(counters)) {
            CounterQueue queueAssigned = TokenUtils.getAvailableQueue(counters);
            queueAssigned.offer(token);
            token.setCounterAssigned(queueAssigned.getCounterId());
        } else {
            if (token.getPriority().equals(PriorityType.PREMIUM)) {
                premiumQueue.offer(token);
            } else {
                regularQueue.offer(token);
            }
        }
    }

    public static void dequeToken(Token token, List<CounterQueue> counters) {
        for (CounterQueue counter : counters) {
            if (counter.getCounterId().equals(token.getCounterAssigned())) {
                counter.poll();
            }
        }
    }

    public static void forwardToken(Token token, List<CounterQueue> counters) {
        //In case of forwarding, next counter number should be specified in action items
        //like "Forwarded to c2"
        String actionItem = token.getActionItems();
        if (!ObjectUtils.isEmpty(actionItem)) {
            for (CounterQueue counter : counters) {
                if (counter.getCounterId().toLowerCase().equals(actionItem.substring(actionItem.length() - 2).toLowerCase())) {
                    counter.offer(token);
                    return;
                }
            }
        }
    }
}
