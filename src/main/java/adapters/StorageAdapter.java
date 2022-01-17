package adapters;

import domain.model.TokenSet;
import domain.ports.IStorageAdapter;
import storage.ITokenStorage;
import storage.TokenStorage;

public class StorageAdapter implements IStorageAdapter {

    private ITokenStorage externalStorage = new TokenStorage();

    @Override
    public int storageCheckCustomerTokenSize(String cid) {
        return externalStorage.getCustomerTokenSetSize(cid);
    }

    @Override
    public boolean storageCheckCustomerToken(String cid, String token) {
        return externalStorage.isCustomerTokenValid(cid, token);
    }

    @Override
    public void storageStoreTokens(String cid, TokenSet tokens) {
        externalStorage.addTokens(cid, tokens);
    }

    @Override
    public void storageConsumeToken(String cid, String token) {
        externalStorage.removeTokenFromCustomer(cid, token);
    }

    @Override
    public void storageAddNewCustomer(String cid, TokenSet tokens) {
        externalStorage.addNewEntry(cid, tokens);

    }

    @Override
    public ITokenStorage getExternalStorage() {
        return externalStorage;
    }


}
