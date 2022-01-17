package domain;

import adapters.StorageAdapter;
import domain.model.TokenSet;
import domain.ports.IStorageAdapter;

public class TokenManager {

    private TokenGenerator generator = new TokenGenerator();
    private IStorageAdapter adapter = new StorageAdapter();

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

    //has to communicate with storage
    public boolean validateToken(String cid, String token){return false;}

    //this should generate a set of tokens specified by amount
    public void generatetokens(int amount){}

    //this should return a tokenset to the customer who requests some tokens
    public TokenSet supplyTokens(){ return new TokenSet();}

    //this should contact storage to see how many tokens a given customer has
    private int checkCustomerTokensetSize(String cid){return 0;}

    //this should store tokens in the db (we dont know if we should just store one or multiple at a time)
    public void storeTokens(TokenSet tokens){}

    //should remove a single token from the storage.
    private void consumeToken(String cid, String Token){}




}
