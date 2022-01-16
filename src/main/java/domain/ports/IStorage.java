package domain.ports;

import domain.model.TokenSet;

public interface IStorage {

    boolean storageCheckCustomerToken(String cid, String token);
    void storageStoreTokens(TokenSet tokens);
    void storageConsumeToken(String cid, String token);

}
