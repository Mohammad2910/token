package tokens;

import adapters.StorageAdapter;
import domain.TokenGenerator;
import domain.model.TokenSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.ITokenStorage;
import storage.TokenStorage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Renjue, Christian and David
 */
class StorageAdapterTest {

    TokenGenerator generator = new TokenGenerator();
    StorageAdapter adapter = new StorageAdapter();


    @Test
    void storageCheckCustomerTokenSize() {
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(generator.generate());
        adapter.storageAddNewCustomer(cid, set);
        assertEquals(1, adapter.storageCheckCustomerTokenSize(cid));
    }

    @Test
    void storageCheckCustomerToken() {
        String cid = "cid1";
        String token = "token1";
        TokenSet set = new TokenSet();
        set.add(token);
        adapter.storageAddNewCustomer(cid, set);
        assertTrue(adapter.storageCheckCustomerToken(cid,token));
    }

    @Test
    void storageStoreTokens() {
        //add one initial tokenSet with one single token
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(generator.generate());
        adapter.storageAddNewCustomer(cid, set);

        //now we add a new tokenset to the initial tokenSet
        TokenSet newSet = new TokenSet();
        newSet.add(generator.generate());
        newSet.add(generator.generate());
        adapter.storageStoreTokens(cid, newSet);

        //now there should be a total of 3 tokens in the tokenSet
        assertEquals(3, adapter.storageCheckCustomerTokenSize(cid));
    }

    @Test
    void storageConsumeToken() {
        //Creates a customer with a tokenSet of 2 tokens
        String cid = "cid1";
        String token1 = "token1";
        String token2 = "token2";
        TokenSet set = new TokenSet();
        set.add(token1);
        set.add(token2);
        adapter.storageAddNewCustomer(cid, set);

        //now we consume token2 and checks that it was removed
        adapter.storageConsumeToken(cid, token2);
        assertFalse(adapter.storageCheckCustomerToken(cid, token2));

    }

    @Test
    void storageAddNewCustomer() {
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(generator.generate());
        adapter.storageAddNewCustomer(cid, set);
        assertNotNull(adapter.getExternalStorage().getTokenHashMap().get(cid));
    }

    @Test
    void getExternalStorage(){
        assertNotNull(adapter.getExternalStorage());
    }
}