package storage;

import domain.model.TokenSet;
import java.util.HashMap;

/**
 * Class that represents the storage unit of customer-tokenSet mappings
 * @author Renjue, Christian and David
 */
public class TokenStorage {

    HashMap<String, TokenSet> tokenHashMap = new HashMap<>();

    /**
     * Adds a new customer-tokenSet mapping to the storage
     * @param cid of the customer to add
     * @param tokens that the customer possess
     */
    public void addNewEntry(String cid, TokenSet tokens){

        tokenHashMap.put(cid, tokens);
    }

    /**
     * Method for adding a new set of tokens to a customers existing tokenSet
     * @param cid of the customer
     * @param tokens to add to the already existing tokenSet
     */
    public void addTokens(String cid, TokenSet tokens){

        TokenSet tokenSetFromStorage = tokenHashMap.get(cid);
        for (String token: tokens.getSet()) {tokenSetFromStorage.add(token);}
    }


    /**
     * Method for removing a token from a specified customers tokenSet
     * @param cid of the customer
     * @param token to be removed from the customer's tokenSet
     */
    public void removeTokenFromCustomer(String cid, String token){
        TokenSet set = tokenHashMap.get(cid);
        set.remove(token);
    }

    /**
     * Method for getting the amount of tokens in a specified customer's tokenSet
     * @param cid of the customer
     * @return Integer specifying the amount of tokens in the tokenSet
     */
    public int getCustomerTokenSetSize(String cid){

       TokenSet tokenSet = tokenHashMap.get(cid);
       return tokenSet.numberOfTokens();
    }

    /**
     * Method for checking if a specified token exists in a specified customer's tokenSet
     * @param cid of the customer
     * @param token to be checked
     * @return boolean stating whether the specified token did exist or not
     */
    public boolean isCustomerTokenValid(String cid, String token){
        TokenSet set = tokenHashMap.get(cid);
        return set.searchForToken(token);
    }

    /**
     * Method for getting the whole hashmap (storage) of all customer-tokenSet mappings
     * @return Hashmap (storage)
     */
    public HashMap<String, TokenSet> getTokenHashMap(){return tokenHashMap;}
}
