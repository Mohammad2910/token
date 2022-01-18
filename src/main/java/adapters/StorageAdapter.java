package adapters;

import domain.model.TokenSet;
import domain.ports.IStorageAdapter;
import storage.ITokenStorage;

public class StorageAdapter implements IStorageAdapter {

    private ITokenStorage tokenStorage;

    public StorageAdapter(ITokenStorage tokenStorage) {
        this.tokenStorage = tokenStorage;
    }

    @Override
    public int storageCheckCustomerTokenSize(String cid) {
        return tokenStorage.getCustomerTokenSetSize(cid);
    }

    @Override
    public boolean storageCheckCustomerToken(String cid, String token) {
        return tokenStorage.isCustomerTokenValid(cid, token);
    }

    @Override
    public boolean isCustomerCreatedInStorage(String cid){ return tokenStorage.isCustomerCreated(cid); }

    @Override
    public void storageStoreTokens(String cid, TokenSet tokens) {
        tokenStorage.addTokens(cid, tokens);
    }

    @Override
    public void storageConsumeToken(String cid, String token) {
        tokenStorage.removeTokenFromCustomer(cid, token);
    }

    @Override
    public void storageAddNewCustomer(String cid, TokenSet tokens) {
        tokenStorage.addNewEntry(cid, tokens);

    }

//    @Override
//    public ITokenStorage getExternalStorage() {
//        return externalStorage;
//    }
//

}
