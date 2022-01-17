package adapters;

import domain.model.TokenSet;
import domain.ports.IStorageAdapter;

public class StorageAdapter implements IStorageAdapter {


    @Override
    public int storageCheckCustomerTokenSize(String cid) {
        return 0;
    }

    @Override
    public boolean storageCheckCustomerToken(String cid, String token) {
        return false;
    }

    @Override
    public void storageStoreTokens(TokenSet tokens) {

    }

    @Override
    public void storageConsumeToken(String cid, String token) {

    }

    @Override
    public void storageAddNewCustomer(String cid, TokenSet tokens) {

    }
}
