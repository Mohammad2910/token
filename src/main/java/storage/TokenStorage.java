package storage;

import domain.model.TokenSet;

import java.util.HashMap;

public class TokenStorage {

    HashMap<String, TokenSet> tokenHashMap = new HashMap<>();

    //adding a new customer
    public void addNewEntry(String cid, TokenSet tokens){

        tokenHashMap.put(cid, tokens);
    }

    //adding new tokens to an already existing customer
    public void addTokens(String cid, TokenSet tokens){

        TokenSet tokenSetFromStorage = tokenHashMap.get(cid);
        //tokenSetFromStorage.add(tokens.);


    }

    //removes a token from
    public void removeTokenFromCustomer(){}

    //return the size of a specified customer's tokenset
    public int getCustomerTokenSetSize(String cid){

       TokenSet tokenSet = tokenHashMap.get(cid);
       return tokenSet.numberOfTokens();
    }

    //checks if a specified token exists
    public boolean isCustomerTokenValid(String cid, String token){return false;}

    //getter for the whole hashmap (storage)
    public HashMap<String, TokenSet> getTokenHashMap(){return tokenHashMap;}






}
