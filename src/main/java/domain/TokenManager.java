package domain;

import adapters.StorageAdapter;
import domain.model.TokenSet;
import domain.ports.IStorageAdapter;

public class TokenManager {

    private TokenGenerator generator = new TokenGenerator();
    private IStorageAdapter adapter = new StorageAdapter();

    /**
     * Method for checking if the specified customer has had a tokenSet in storage
     * @param cid of the specified customer
     * @return boolean stating if the specified customer has had a tokenSet in storage or not
     */
    public boolean checkCustomerTokenSet(String cid){
        if(adapter.getExternalStorage().getTokenHashMap().get(cid) != null){
            return true;
        }
        return false;
    }

    /**
     * Method for adding a new customer with a specified tokenSet
     * @param cid of the customer to add
     * @param tokens that the customer possess
     * @return boolean stating whether the customer is successfully added or not
     */
    public boolean addNewCustomer(String cid, TokenSet tokens){
        if(adapter.getExternalStorage().getTokenHashMap().get(cid) == null){
            adapter.storageAddNewCustomer(cid, tokens);
            return true;
        }
        return false;
    }

    /**
     * Method for validating a specified customer with a specified token
     * @param cid of the specified customer
     * @param token that the customer possess
     * @return boolean stating whether the possessed token is valid or not
     */
    public boolean validateToken(String cid, String token){
        if(adapter.getExternalStorage().getTokenHashMap().get(cid) == null){
            return false;
        }
        return adapter.storageCheckCustomerToken(cid, token);
    }

    /**
     * Method for generating a tokenSet specified by amount
     * @param amount of tokens to be generated
     * @return a tokenSet specified by amount
     */
    public TokenSet generateTokens(int amount){
        TokenSet set = new TokenSet();
        for(int i = 0; i < amount; i++){
            set.add(generator.generate());
        }
        return set;
    }

    //this should return a tokenset to the customer who requests some tokens
    public TokenSet supplyTokens(){ return new TokenSet();}

    //this should contact storage to see how many tokens a given customer has
    private int checkCustomerTokensetSize(String cid){return 0;}

    //this should store tokens in the db (we dont know if we should just store one or multiple at a time)
    public void storeTokens(String cid, TokenSet tokens){
        adapter.storageStoreTokens(cid, tokens);
    }

    //should remove a single token from the storage.
    private void consumeToken(String cid, String Token){}




}
