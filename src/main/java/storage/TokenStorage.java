package storage;

import domain.model.TokenSet;
import java.util.HashMap;

/**
 * Class that represents the storage unit of customer-tokenSet mappings
 * @author Renjue, Christian and David
 */
public class TokenStorage implements ITokenStorage{

    HashMap<String, TokenSet> tokenHashMap = new HashMap<>();

    public void addNewEntry(String cid, TokenSet tokens){

        tokenHashMap.put(cid, tokens);
    }
    public TokenSet addTokens(String cid, TokenSet tokens){

        TokenSet tokenSetFromStorage = tokenHashMap.get(cid);
        for (String token: tokens.getSet()) {
            if(token != null){
                tokenSetFromStorage.add(token);
            }
        }
        return tokenSetFromStorage;
    }
    public void removeTokenFromCustomer(String cid, String token){
        TokenSet set = tokenHashMap.get(cid);
        set.remove(token);
    }
    public int getCustomerTokenSetSize(String cid){

       TokenSet tokenSet = tokenHashMap.get(cid);
       return tokenSet.numberOfTokens();
    }

    //todo: throw an exception here if customer token is not valid
    public boolean isCustomerTokenValid(String cid, String token){
        TokenSet set = tokenHashMap.get(cid);
        return set.searchForToken(token);
    }

    @Override
    public boolean isCustomerCreated(String cid) {
        return tokenHashMap.get(cid) != null;
    }

    public HashMap<String, TokenSet> getTokenHashMap(){return tokenHashMap;}
}
