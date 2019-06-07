package com.abc.service;

import com.abc.model.CounterQueue;
import com.abc.model.PremiumQueue;
import com.abc.model.RegularQueue;
import com.abc.model.Token;
import com.abc.util.PriorityType;
import com.abc.util.Status;
import com.abc.util.TokenStub;
import com.abc.util.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {


    @Override
    public Map<String, List<String>> list() {

        //Preparing tokens which are there counter wise
        List<CounterQueue> counters = TokenStub.getQueues();
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

        Map<Long, Token> tokensMap = TokenStub.getTokens();
        if (token.getStatus().equals(Status.OPEN)) {
            return TokenStub.update(id, token);
        } else {
            if (token.getStatus().equals(Status.FORWARDED)) {
                TokenUtils.assignTokenToQueue(token,TokenStub.getQueues());
            }
            TokenUtils.dequeToken(token,TokenStub.getQueues());
            if(!premiumQueue.isEmpty()){
                TokenUtils.assignTokenToQueue((Token)premiumQueue.poll(),TokenStub.getQueues());
            }
            else if(!regularQueue.isEmpty()){
                TokenUtils.assignTokenToQueue((Token)regularQueue.poll(),TokenStub.getQueues());
            }
        }
        return token;
    }

    @Override
    public Token createToken(Token token) {
        Token createdToken = TokenStub.create(token);

        TokenUtils.assignTokenToQueue(createdToken,TokenStub.getQueues());

        return createdToken;
    }

    @Override
    public Token deleteToken(Long id) {
        return TokenStub.delete(id);
    }

    @Override
    public Token getTokenById(Long id) {
        return TokenStub.get(id);
    }

    @Override
    public PriorityType getTokenPriorityType(Long id) {
        return TokenStub.get(id).getPriority();
    }
}
