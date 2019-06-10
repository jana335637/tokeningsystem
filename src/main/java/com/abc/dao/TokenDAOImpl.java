package com.abc.dao;

import com.abc.model.CounterQueue;
import com.abc.model.Token;
import com.abc.util.TokenStub;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TokenDAOImpl implements TokenDAO {
    public List<CounterQueue> getQueues() {
        return TokenStub.getQueues();
    }

    public Map<Long, Token> getTokens() {
        return TokenStub.getTokens();
    }

    public Token create(Token token) {
        return TokenStub.create(token);
    }

    public Token get(Long id) {
        return TokenStub.get(id);
    }

    public Token update(Long id, Token token) {
        return TokenStub.update(id, token);
    }

    public Token delete(Long id) {
        return TokenStub.delete(id);
    }
}
