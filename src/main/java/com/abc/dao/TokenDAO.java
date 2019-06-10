package com.abc.dao;

import com.abc.model.CounterQueue;
import com.abc.model.Token;

import java.util.List;
import java.util.Map;

public interface TokenDAO {
    public List<CounterQueue> getQueues();

    public Map<Long, Token> getTokens();

    public Token create(Token token);

    public Token get(Long id);

    public Token update(Long id, Token token);

    public Token delete(Long id);
}
