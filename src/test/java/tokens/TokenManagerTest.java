package tokens;

import adapters.StorageAdapter;
import domain.TokenGenerator;
import domain.TokenManager;
import domain.model.TokenSet;
import org.junit.jupiter.api.Test;
import storage.TokenStorage;

import static org.junit.jupiter.api.Assertions.*;

class TokenManagerTest {

    TokenGenerator generator = new TokenGenerator();
    TokenManager manager = new TokenManager();

    @Test
    void checkCustomerTokenSet(){
        String cid = "cid1";
        TokenSet set = new TokenSet();
        manager.addNewCustomer(cid, set);
        assertTrue(manager.checkCustomerTokenSet(cid));
        assertFalse(manager.checkCustomerTokenSet("cid2"));
    }

    @Test
    void addNewCustomer(){
        String cid = "cid1";
        TokenSet set = new TokenSet();

        //customer is a new customer
        assertTrue(manager.addNewCustomer(cid, set));
        //customer has existed in storage
        assertFalse(manager.addNewCustomer(cid,set));
    }


    @Test
    void validateToken(){
        String cid = "cid1";
        String token = "token2";
        TokenSet set = new TokenSet();
        set.add("token1");
        set.add(token);
        set.add("token3");
        manager.addNewCustomer(cid, set);
        assertTrue(manager.validateToken(cid, token));
        assertFalse(manager.validateToken(cid, "token4"));
        assertFalse(manager.validateToken("cid2", token));
    }

    @Test
    void generateToken(){
        int amount = 3;
        assertEquals(3, manager.generateTokens(amount).numberOfTokens());
    }

    @Test
    void supplyTokens(){
    }

    @Test
    void storeTokens(){
        String cid = "cid1";
        TokenSet set = new TokenSet();
        manager.storeTokens(cid, set);
        // assertNotNull();

    }

    @Test
    void consumeToken(){}



}