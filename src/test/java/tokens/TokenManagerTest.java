package tokens;

import adapters.StorageAdapter;
import domain.TokenGenerator;
import domain.TokenManager;
import domain.model.TokenSet;
import exceptions.AmountNotValid;
import exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import storage.TokenStorage;

import static org.junit.jupiter.api.Assertions.*;

class TokenManagerTest {

    TokenGenerator generator = new TokenGenerator();
    TokenManager manager = TokenManager.getManager();

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
        //token does not match
        assertFalse(manager.validateToken(cid, "token4"));
        //customer does not exist in storage
        assertFalse(manager.validateToken("cid2", token));
    }

    @Test
    void generateToken(){
        int amount = 3;
        assertEquals(3, manager.generateTokens(amount).numberOfTokens());
    }

    @Test
    void checkCustomerTokenSetSize() throws NotFoundException {
        //customer is in storage
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(generator.generate());
        set.add(generator.generate());
        set.add(generator.generate());
        set.add(generator.generate());
        manager.addNewCustomer(cid, set);
        assertEquals(4, manager.checkCustomerTokenSetSize(cid));

        //customer is not in storage
        try{
            manager.checkCustomerTokenSetSize("cid2");
        } catch (NotFoundException e){
            assertTrue(e.getMessage().equals("Customer Not Found"));
        }
    }

    @Test
    void supplyTokens() throws NotFoundException, AmountNotValid {
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(generator.generate());
        manager.addNewCustomer(cid, set);

        TokenSet new_set = manager.supplyTokens(cid, 4);
        assertEquals(5, new_set.numberOfTokens());

    }

    @Test
    void storeTokens() throws NotFoundException {
        String cid = "cid1";
        TokenSet set = new TokenSet();
        manager.addNewCustomer(cid, set);
        set.add("token1");
        manager.storeTokens(cid, set);
        assertEquals(1, manager.checkCustomerTokenSetSize(cid));
    }

    @Test
    void consumeToken(){}



}

