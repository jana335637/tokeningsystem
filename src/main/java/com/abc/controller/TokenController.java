package com.abc.controller;

import com.abc.model.PremiumQueue;
import com.abc.model.Token;
import com.abc.service.CustomerService;
import com.abc.service.TokenService;
import com.abc.util.PriorityType;
import com.abc.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/abc/tokens")
public class TokenController {

    @Autowired
    TokenService tokenService;

   /* @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, List<String>> getTokens(){
        premiumQueue.offer(new Token("1", PriorityType.PREMIUM, Status.OPEN,"c101"));
        return (Token)premiumQueue.poll();
    }*/

    @RequestMapping(value = "token", method = RequestMethod.GET)
    public String gettoken(){
        return "Tokens are ready";
    }

    @RequestMapping("")
    public void getToken(){

    }
}
