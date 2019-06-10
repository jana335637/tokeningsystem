package com.abc;

import com.abc.controller.TokenController;
import com.abc.dao.TokenDAO;
import com.abc.model.CounterQueue;
import com.abc.model.Token;
import com.abc.service.TokenServiceImpl;
import com.abc.util.PriorityType;
import com.abc.util.Status;
import com.abc.util.TokenStub;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TokenControllerIntegrationTest {

    @InjectMocks
    TokenServiceImpl tokenService = new TokenServiceImpl();

    @Mock
    TokenDAO tokenDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateToken() {
        Map<Long, Token> tokens = TokenStub.getTokens();
        List<Token> unAssignedTokensBeforeUpdate = tokenService.getUnassignedTokens();
        Token token = new Token(1l, PriorityType.PREMIUM, Status.CANCELLED, 1l);
        token.setCounterAssigned("c1");

        CounterQueue c1 = new CounterQueue("c1", "Sophia");
        CounterQueue c2 = new CounterQueue("c2", "Surya");
        CounterQueue c3 = new CounterQueue("c3", "Sandy");
        List<CounterQueue> counters = new ArrayList<>();
        counters.add(c1);
        counters.add(c2);
        counters.add(c3);

        when(tokenDAO.getQueues()).thenReturn(counters);

        tokenService.updateToken(1l, token);

        List<Token> unAssignedTokensAfterUpdate = tokenService.getUnassignedTokens();

        Assert.assertThat(unAssignedTokensBeforeUpdate.size(), Is.is(Matchers.greaterThan(unAssignedTokensAfterUpdate.size())));
    }
}
