package tokens;

import domain.TokenGenerator;
import domain.model.TokenSet;
import exceptions.TokenOutOfBoundsException;
import exceptions.TokensEnoughException;
import exceptions.TokenNotValidException;
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
        serviceProvider.getTokenManager().addNewCustomer(cid, set);

        //customer is stored
        assertTrue(serviceProvider.getTokenManager().customerExistsInStorage(cid));
        //customer is not stored
        assertFalse(serviceProvider.getTokenManager().customerExistsInStorage("cid2"));
    }

    @Test
    void validateToken() throws TokenNotValidException {
        String cid = "cid1";
        String token = "token2";
        TokenSet set = new TokenSet();
        set.addToken("token1");
        set.addToken(token);
        set.addToken("token3");
        serviceProvider.getTokenManager().addNewCustomer(cid, set);
        assertTrue(serviceProvider.getTokenManager().validateToken(cid, token));
        //token does not match
        assertThrows(TokenNotValidException.class, () -> serviceProvider.getTokenManager().validateToken(cid, "token4"));
    }

    @Test
    void generateToken() {
        int amount = 3;
        assertEquals(3, serviceProvider.getTokenManager().generateTokens(amount).findNumberOfTokens());
    }

    @Test
    void checkCustomerTokenSetSize() {
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.addToken(tokenGenerator.generate());
        set.addToken(tokenGenerator.generate());
        set.addToken(tokenGenerator.generate());
        set.addToken(tokenGenerator.generate());
        serviceProvider.getTokenManager().addNewCustomer(cid, set);
        assertEquals(4, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid));
    }

    /*
     Case 1: Customer requests tokens when:
            - having 0 or 1 token left
            - requested amount of tokens is sufficient
     */
    @Test
    void supplyTokens_Success() {
        try {
            String cid1 = "cid1";
            TokenSet set1 = new TokenSet();
            set1.addToken(tokenGenerator.generate());
            serviceProvider.getTokenManager().addNewCustomer(cid1, set1);
            serviceProvider.getTokenManager().supplyTokens(cid1, 4);
            assertEquals(5, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid1));

        } catch (TokenOutOfBoundsException | TokensEnoughException e) {
            e.printStackTrace();
        }
    }

    /*
     Case 2: Customer requests tokens when:
            - having 0 or 1 tokens left
            - requested amount of tokens exceeds max. limit of tokens.
     */
    @Test
    void supplyTokens_ThrowsTokenOutOfBoundsException() {
        String cid2 = "cid2";
        TokenSet set2 = new TokenSet();
        serviceProvider.getTokenManager().addNewCustomer(cid2, set2);
        assertThrows(TokenOutOfBoundsException.class, () -> serviceProvider.getTokenManager().supplyTokens(cid2, 7));
    }

    /*
     Case 3: Customer requests tokens when
            - having more than 1 token left.
     */
    @Test
    void supplyTokens_ThrowsTokensEnoughException() {
        String cid3 = "cid3";
        TokenSet set3 = new TokenSet();
        set3.addToken(tokenGenerator.generate());
        set3.addToken(tokenGenerator.generate());
        serviceProvider.getTokenManager().addNewCustomer(cid3, set3);
        assertThrows(TokensEnoughException.class, () -> serviceProvider.getTokenManager().supplyTokens(cid3, 1));
    }

    @Test
    void storeTokens() {
        String cid = "cid1";
        TokenSet set1 = new TokenSet();
        set1.addToken(tokenGenerator.generate());
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
        set.addToken(token1);
        set.addToken(token2);
        serviceProvider.getTokenManager().addNewCustomer(cid, set);
        assertEquals(2, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid));
        serviceProvider.getTokenManager().consumeToken(cid, token1);
        assertEquals(1, serviceProvider.getTokenManager().checkCustomerTokenSetSize(cid));
    }
}

