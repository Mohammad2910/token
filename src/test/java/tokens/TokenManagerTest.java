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
    void addNewCustomer(){
        String cid = "cid1";
        TokenSet set = new TokenSet();

        //customer is a new customer
        assertTrue(manager.addNewCustomer(cid, set));
        //customer has existed in storage
        assertFalse(manager.addNewCustomer(cid,set));

    }


    @Test
    void validateToken(){}

    @Test
    void generateToken(){}

    @Test
    void supplyTokens(){
    }

    @Test
    void checkCustomerTokenset(){
    }

    @Test
    void storeTokens(){}

    @Test
    void consumeToken(){}



}