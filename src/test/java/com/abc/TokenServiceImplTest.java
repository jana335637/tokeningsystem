package com.abc;

import com.abc.dao.TokenDAOImpl;
import com.abc.model.CounterQueue;
import com.abc.model.Token;
import com.abc.service.TokenServiceImpl;
import com.abc.util.PriorityType;
import com.abc.util.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class TokenServiceImplTest {

    @InjectMocks
    TokenServiceImpl tokenService;

    @Mock
    TokenDAOImpl tokenDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetToken() {
        Token token = new Token(1l, PriorityType.PREMIUM, Status.OPEN, 1l);
        when(tokenService.getTokenById(1l)).thenReturn(token);

        Assert.assertEquals((long) ((Token) (tokenService.getTokenById(1l))).getId(), 1l);
    }

    @Test
    public void testCreateToken() {
        Token token = new Token(1l, PriorityType.PREMIUM, Status.OPEN, 1l);

        when(tokenDAO.create(token)).thenReturn(token);

        CounterQueue c1 = new CounterQueue("c1", "Sophia");
        CounterQueue c2 = new CounterQueue("c2", "Surya");
        CounterQueue c3 = new CounterQueue("c3", "Sandy");
        List<CounterQueue> counters = new ArrayList<>();
        counters.add(c1);
        counters.add(c2);
        counters.add(c3);

        when(tokenDAO.getQueues()).thenReturn(counters);
        Assert.assertEquals((long) ((Token) (tokenService.createToken(token))).getId(), 1l);
    }

    @Test
    public void testUpdateToken() {
        Token token = new Token(1l, PriorityType.PREMIUM, Status.OPEN, 1l);
        when(tokenService.updateToken(1l, token)).thenReturn(token);

        Assert.assertEquals((long) ((Token) (tokenService.updateToken(1l, token))).getId(), 1l);
    }

    @Test
    public void testGetTokenPriority() {
        Token token = new Token(1l, PriorityType.PREMIUM, Status.OPEN, 1l);
        when(tokenDAO.get(1l)).thenReturn(token);

        Assert.assertEquals((tokenService.getTokenPriorityType(1l)), PriorityType.PREMIUM);
    }
}
