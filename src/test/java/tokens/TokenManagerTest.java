package tokens;

import domain.TokenGenerator;
import domain.model.TokenSet;
import exceptions.AmountNotValidException;
import org.junit.jupiter.api.Test;
import controller.ServiceProvider;

import static org.junit.jupiter.api.Assertions.*;

class TokenManagerTest {

    TokenGenerator tokenGenerator = new TokenGenerator();
    ServiceProvider serviceProvider = new ServiceProvider();

    @Test
    void addNewCustomer() {
        String cid = "cid1";
        TokenSet set = new TokenSet();

        //customer is stored
        assertTrue(serviceProvider.getTokenManager().isCustomerCreatedInStorage(cid));
        //customer is not stored
        assertFalse(serviceProvider.getTokenManager().isCustomerCreatedInStorage("cid2"));
    }


    @Test
    void validateToken() {
        String cid = "cid1";
        String token = "token2";
        TokenSet set = new TokenSet();
        set.add("token1");
        set.add(token);
        set.add("token3");
        serviceProvider.getTokenManager().addNewCustomer(cid, set);
        assertTrue(serviceProvider.getTokenManager().validateToken(cid, token));
        //token does not match
        assertFalse(serviceProvider.getTokenManager().validateToken(cid, "token4"));
    }

    @Test
    void generateToken() {
        int amount = 3;
        assertEquals(3, serviceProvider.getTokenManager().generateTokens(amount).numberOfTokens());
    }

    @Test
    void checkCustomerTokenSetSize() {
        //customer is in storage
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(tokenGenerator.generate());
        set.add(tokenGenerator.generate());
        set.add(tokenGenerator.generate());
        set.add(tokenGenerator.generate());
        serviceProvider.getTokenManager().addNewCustomer(cid, set);
        assertEquals(4, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid));
    }

    @Test
    void supplyTokens() throws AmountNotValidException {
        // case1: customer has existed in storage, request tokens when there is 0 or 1 token left and the resulted amount of tokens will not exceed the maximum
        String cid1 = "cid1";
        TokenSet set1 = new TokenSet();
        set1.add(tokenGenerator.generate());
        serviceProvider.getTokenManager().addNewCustomer(cid1, set1);
        serviceProvider.getTokenManager().supplyTokens(cid1, 4);
        assertEquals(5, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid1));

        // case2: customer has existed in storage, request tokens when there is 0 or 1 token left but request too many tokens
        String cid2 = "cid2";
        TokenSet set2 = new TokenSet();
        serviceProvider.getTokenManager().addNewCustomer(cid2, set2);
        try {
            serviceProvider.getTokenManager().supplyTokens(cid2, 7);
        } catch (AmountNotValidException e) {
            assertTrue(e.getMessage().equals("customer request too many tokens"));
        }

        // case3: customer has existed in storage but request tokens when there is more than 1 token left
        String cid3 = "cid3";
        TokenSet set3 = new TokenSet();
        set3.add(tokenGenerator.generate());
        set3.add(tokenGenerator.generate());
        serviceProvider.getTokenManager().addNewCustomer(cid3, set3);
        try {
            serviceProvider.getTokenManager().supplyTokens(cid3, 1);
        } catch (AmountNotValidException e2) {
            assertTrue(e2.getMessage().equals("customer cannot request tokens"));
        }
    }

    @Test
    void storeTokens() throws NotFoundException {
        String cid = "cid1";
        TokenSet set1 = new TokenSet();
        set1.add(tokenGenerator.generate());
        serviceProvider.getTokenManager().addNewCustomer(cid, set1);

        TokenSet set2 = serviceProvider.getTokenManager().generateTokens(3);

        serviceProvider.getTokenManager().storeTokens(cid, set2);
        assertEquals(4, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid));
    }

    @Test
    void consumeToken() {
        String cid = "cid1";
        TokenSet set = new TokenSet();
        String token1 = tokenGenerator.generate();
        String token2 = tokenGenerator.generate();
        set.add(token1);
        set.add(token2);
        serviceProvider.getTokenManager().addNewCustomer(cid, set);
        assertEquals(2, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid));
        serviceProvider.getTokenManager().consumeToken(cid, token1);
        assertEquals(1, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid));
    }
}

