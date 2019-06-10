package com.abc.service;

import com.abc.dao.TokenDAO;
import com.abc.model.CounterQueue;
import com.abc.model.PremiumQueue;
import com.abc.model.RegularQueue;
import com.abc.model.Token;
import com.abc.util.PriorityType;
import com.abc.util.Status;
import com.abc.util.TokenStub;
import com.abc.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenDAO tokenDAO;

    @Override
    public Map<String, List<String>> list() {

        //Preparing tokens which are there counter wise
        List<CounterQueue> counters = tokenDAO.getQueues();
        Map<String, List<String>> tokens = new HashMap<>();
        String prefix;
        for (CounterQueue counterQueue : counters) {
            List<String> counterTokens = new ArrayList<>();
            for (int i = 0; i < counterQueue.size(); i++) {
                Token currentToken = ((Token) counterQueue.get(i));
                if (currentToken.getPriority().equals(PriorityType.PREMIUM))
                    prefix = "P";
                else
                    prefix = "R";
                counterTokens.add(prefix + currentToken.getId());
            }
            tokens.put(counterQueue.getCounterId(), counterTokens);
        }
        return tokens;
    }

    @Override
    public Token updateToken(Long id, Token token) {
        PremiumQueue premiumQueue = PremiumQueue.getPremiumQueue();
        RegularQueue regularQueue = RegularQueue.getRegularQueue();

        tokenDAO.update(id, token);
        if (!(token.getStatus().equals(Status.OPEN))) {
            TokenUtils.dequeToken(token, tokenDAO.getQueues());
            if (token.getStatus().equals(Status.FORWARDED)) {
                TokenUtils.forwardToken(token, tokenDAO.getQueues());
            } else {
                if (!premiumQueue.isEmpty()) {
                    TokenUtils.assignTokenToQueue((Token) premiumQueue.poll(), tokenDAO.getQueues());
                } else if (!regularQueue.isEmpty()) {
                    TokenUtils.assignTokenToQueue((Token) regularQueue.poll(), tokenDAO.getQueues());
                }
            }
        }
        return token;
    }

    @Override
    public Token createToken(Token token) {
        Token createdToken = tokenDAO.create(token);

        TokenUtils.assignTokenToQueue(createdToken, tokenDAO.getQueues());

        return createdToken;
    }

    @Override
    public Token deleteToken(Long id) {
        return tokenDAO.delete(id);
    }

    @Override
    public Token getTokenById(Long id) {
        return tokenDAO.get(id);
    }

    @Override
    public PriorityType getTokenPriorityType(Long id) {
        return tokenDAO.get(id).getPriority();
    }

    @Override
    public List<Token> getUnassignedTokens() {
        PremiumQueue premiumQueue = PremiumQueue.getPremiumQueue();
        RegularQueue regularQueue = RegularQueue.getRegularQueue();
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < premiumQueue.size(); i++) {
            tokens.add((Token) premiumQueue.get(i));
        }
        for (int i = 0; i < regularQueue.size(); i++) {
            tokens.add((Token) regularQueue.get(i));
        }
        return tokens;
    }

    @Override
    public Map<Long, Token> getAllTokens() {
        return TokenStub.getAllTokens();
    }
}
