package domain.ports;

import domain.model.TokenSet;

public interface IStorageAdapter {

    int storageCheckCustomerTokenSize(String cid);
    boolean storageCheckCustomerToken(String cid, String token);
    void storageStoreTokens(TokenSet tokens);
    void storageConsumeToken(String cid, String token);
    void storageAddNewCustomer(String cid, TokenSet tokens);

}
