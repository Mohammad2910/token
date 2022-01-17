package storage;

import domain.model.TokenSet;

import java.util.HashMap;

public class TokenStorage {

    HashMap<String, TokenSet> tokenHashMap = new HashMap<>();

    //adding a new customer
    public void addNewEntry(String cid, TokenSet tokens){

        tokenHashMap.put(cid, tokens);
    }

    /**
     *
     * @param cid
     * @param tokens
     */
    //adding new tokens to an already existing customer
    public void addTokens(String cid, TokenSet tokens){

        TokenSet tokenSetFromStorage = tokenHashMap.get(cid);
        for (String token: tokens.getSet()) {tokenSetFromStorage.add(token);}
    }

    //removes a token from
    public void removeTokenFromCustomer(String cid, String token){
        TokenSet set = tokenHashMap.get(cid);
        set.remove(token);
    }

    /**
     *
     * @param cid
     * @return
     */
    //return the size of a specified customer's tokenset
    public int getCustomerTokenSetSize(String cid){

       TokenSet tokenSet = tokenHashMap.get(cid);
       return tokenSet.numberOfTokens();
    }

    /**
     *
     * @param cid
     * @param token
     * @return
     */
    //checks if a specified token exists
    public boolean isCustomerTokenValid(String cid, String token){
        TokenSet set = tokenHashMap.get(cid);
        return set.searchForToken(token);
    }

    /**
     *
     * @return
     */
    //getter for the whole hashmap (storage)
    public HashMap<String, TokenSet> getTokenHashMap(){return tokenHashMap;}






}
