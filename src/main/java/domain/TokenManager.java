package domain;

import adapters.StorageAdapter;
import domain.model.TokenSet;
import domain.ports.IStorageAdapter;
import exceptions.AmountNotValid;
import exceptions.NotFoundException;

public class TokenManager {

    private TokenGenerator generator = new TokenGenerator();
    private IStorageAdapter adapter = new StorageAdapter();
    private static TokenManager manager = new TokenManager();

    private TokenManager(){}

    public static TokenManager getManager(){return manager;}

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
     * Method for generating a tokenSet specified by amount without any checking
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

    /**
     * Method for checking the amount of tokens that a specified customer has
     * @param cid of the specified customer
     * @return the integer amount of tokens that the customer possess
     * @throws NotFoundException if the customer has not had a tokenSet in storage
     */
    public int checkCustomerTokenSetSize(String cid) throws NotFoundException {
        if(adapter.getExternalStorage().getTokenHashMap().get(cid) == null){
            throw new NotFoundException("Customer Not Found");
        }
        return adapter.storageCheckCustomerTokenSize(cid);
    }

    /**
     * Method for returning the updated tokenSet after a specified customer's request
     * @param cid of the specified customer
     * @param amount that the customer request
     * @return
     * @throws NotFoundException
     * @throws AmountNotValid if the customer cannot request the specified amount
     */
    public TokenSet supplyTokens(String cid, int amount) throws NotFoundException, AmountNotValid {

         if(manager.checkCustomerTokenSet(cid)){  // the customer has had a tokenSet in storage
            if(manager.checkCustomerTokenSetSize(cid) < 2){  // the customer has 0 or 1 token
                if(manager.checkCustomerTokenSetSize(cid) + amount < 7){  // the maximal amount of tokens should not exceed 6
                   return adapter.getExternalStorage().addTokens(cid, manager.generateTokens(amount));
                }
                else {
                    throw new AmountNotValid("customer request too many tokens");
                }
            }
            else {
                throw new AmountNotValid("customer cannot request tokens");
            }
         }
         else {  // the customer does not have a tokenSet in storage
             if(amount < 7){
                 // add a new customer in storage
                manager.addNewCustomer(cid, manager.generateTokens(amount));
                return adapter.getExternalStorage().getTokenHashMap().get(cid);
            }
             else {
                 throw new AmountNotValid("customer request too many tokens");
             }
         }
    }

    //this should store tokens in the db (we dont know if we should just store one or multiple at a time)
    public void storeTokens(String cid, TokenSet tokens){
        adapter.storageStoreTokens(cid, tokens);
    }

    //should remove a single token from the storage.
    private void consumeToken(String cid, String Token){}


}
