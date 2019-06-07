package com.abc.controller;

import com.abc.model.Customer;
import com.abc.model.PremiumQueue;
import com.abc.model.RegularQueue;
import com.abc.model.Token;
import com.abc.service.TokenService;
import com.abc.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/abc")
public class TokenController {

    @Autowired
    TokenService tokenService;


    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public ResponseEntity list() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tokenService.list());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/queue/tokens", method = RequestMethod.GET)
    public ResponseEntity getTokensUnassigned() {
        try {
            PremiumQueue premiumQueue = PremiumQueue.getPremiumQueue();
            RegularQueue regularQueue = RegularQueue.getRegularQueue();
            List<Token> tokens = new ArrayList<>();
            for (int i = 0; i < premiumQueue.size(); i++) {
                tokens.add((Token) premiumQueue.get(i));
            }
            for (int i = 0; i < regularQueue.size(); i++) {
                tokens.add((Token) regularQueue.get(i));
            }
            return ResponseEntity.status(HttpStatus.OK).body(tokens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/tokens/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable Long id) {
        try {
            Token token = tokenService.getTokenById(id);
            if (ObjectUtils.isEmpty(token))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/tokens/{id}/Type", method = RequestMethod.GET)
    public ResponseEntity getTokenPriorityType(@PathVariable Long id) {
        try {
            PriorityType priorityType = tokenService.getTokenPriorityType(id);
            if (ObjectUtils.isEmpty(priorityType))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(priorityType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Token token) {
        try {
            Customer customer = CustomerStub.get(token.getCustId());
            if (ObjectUtils.isEmpty(customer))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Customer Id, Please check");

            return ResponseEntity.status(HttpStatus.CREATED).body(tokenService.createToken(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/tokens/{id}", method = RequestMethod.PUT)
    public ResponseEntity get(@PathVariable Long id, @RequestBody Token token) {
        try {

            if(TokenStub.getTokens().get(token.getId())==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found with id:" + id);

            if(!TokenUtils.isValidTokenToServe(TokenStub.getQueues(),token)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token with id:" + id+" is not valid one to serve");
            }

            //Checking the status has valid value or not. If not giving Bad request response back
            if (!(token.getStatus() instanceof Status))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token Status should be OPEN/COMPLETED/CANCELLED/FORWARDED. Found invalid Status, Please check.");

            Token updatedToken = tokenService.updateToken(id, token);

            return ResponseEntity.status(HttpStatus.OK).body(updatedToken);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/tokens/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            Token token = tokenService.deleteToken(id);
            if (ObjectUtils.isEmpty(token))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token not found with id:" + id);

            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}
