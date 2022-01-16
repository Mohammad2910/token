package adapters;

import domain.model.TokenSet;
import domain.ports.IStorage;

public class Storage implements IStorage {
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
}
