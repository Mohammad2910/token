package storage;

import domain.model.TokenSet;

import java.util.HashMap;

public class TokenStorage {

    HashMap<String, TokenSet> tokenHashMap = new HashMap<>();

    //adding a new customer
    public void addNewEntry(String cid, TokenSet tokens){}

    //adding new tokens to an already existing customer
    public void addTokens(){}

    //removes a token from
    public void removeTokenFromCustomer(){}

    //return the size of a specified customer's tokenset
    public int getCustomerTokenSetSize(String cid){}

    //checks if a specified token exists
    public boolean isCustomerTokenValid(String cid, String token){}






}
