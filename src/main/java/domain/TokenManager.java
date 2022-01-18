package domain;

import domain.model.TokenSet;
import domain.ports.IStorageAdapter;
import exceptions.AmountNotValid;
import exceptions.NotFoundException;

/**
 * Class for managing tokens
 *
 * @author Renjue, Christian and David
 */
public class TokenManager {

    private TokenGenerator generator = new TokenGenerator();
    private IStorageAdapter iStorageAdapter;

    public TokenManager(IStorageAdapter iStorageAdapter) {
        this.iStorageAdapter = iStorageAdapter;
    }


    /**
     * Method for adding a new customer with a specified tokenSet
     * @param cid    of the customer to add
     * @param tokens that the customer possess
     */
    public void addNewCustomer(String cid, TokenSet tokens) {
        iStorageAdapter.storageAddNewCustomer(cid, tokens);
    }

    /**
     * Method for validating a specified customer with a specified token
     *
     * @param cid   of the specified customer
     * @param token that the customer possess
     * @return boolean stating whether the possessed token is valid or not
     */
    public boolean validateToken(String cid, String token) {
        return iStorageAdapter.storageCheckCustomerToken(cid, token);
    }

    /**
     * Method for generating a tokenSet specified by amount without any checking
     *
     * @param amount of tokens to be generated
     * @return a tokenSet specified by amount
     */
    public TokenSet generateTokens(int amount) {
        TokenSet set = new TokenSet();
        for (int i = 0; i < amount; i++) {
            set.add(generator.generate());
        }
        return set;
    }

    /**
     * Method for checking the amount of tokens that a specified customer has
     *
     * @param cid of the specified customer
     * @return the integer amount of tokens that the customer possess
     */
    public int checkCustomerTokenSetSize(String cid) {
        return iStorageAdapter.storageCheckCustomerTokenSize(cid);
    }

    /**
     * Method for returning the updated tokenSet after a specified customer's request
     *
     * @param cid    of the specified customer
     * @param amount that the customer request
     * @throws AmountNotValidException if the customer cannot request the specified amount
     */
    public void supplyTokens(String cid, int amount) throws AmountNotValidException {
        if (checkCustomerTokenSetSize(cid) < 2) {  // the customer has 0 or 1 token
            if (checkCustomerTokenSetSize(cid) + amount < 7) {  // the maximal amount of tokens should not exceed 6
                iStorageAdapter.storageStoreTokens(cid, generateTokens(amount));
            } else {
                throw new AmountNotValidException("customer request too many tokens");
            }
        } else {
            throw new AmountNotValidException("customer cannot request tokens");
        }
    }

    /**
     * Method for storing a new TokenSet with specified amount of tokens in already existing storage
     *
     * @param cid    of the specified customer
     * @param tokens that need to be stored in storage
     */
    public void storeTokens(String cid, TokenSet tokens) {
        iStorageAdapter.storageStoreTokens(cid, tokens);
    }

    /**
     * Method for consuming a token from customer
     * @param cid of the specified customer
     * @param token that need to be consumed from storage
     */
    public void consumeToken(String cid, String token) {
        iStorageAdapter.storageConsumeToken(cid, token);
    }

}
