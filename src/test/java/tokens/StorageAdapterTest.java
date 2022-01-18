package tokens;

import controller.ServiceProvider;
import domain.TokenGenerator;
import domain.model.TokenSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Renjue, Christian and David
 */
class StorageAdapterTest {

    TokenGenerator generator = new TokenGenerator();
    ServiceProvider serviceProvider = new ServiceProvider();


    @Test
    void storageCheckCustomerTokenSize() {
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(generator.generate());
        serviceProvider.getStorageAdapter().storageAddNewCustomer(cid, set);
        assertEquals(1, serviceProvider.getStorageAdapter().storageCheckCustomerTokenSize(cid));
    }

    @Test
    void storageCheckCustomerToken() {
        String cid = "cid1";
        String token = "token1";
        TokenSet set = new TokenSet();
        set.add(token);
        serviceProvider.getStorageAdapter().storageAddNewCustomer(cid, set);
        assertTrue(serviceProvider.getStorageAdapter().storageCheckCustomerToken(cid,token));
    }

    @Test
    void storageStoreTokens() {
        //add one initial tokenSet with one single token
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(generator.generate());
        serviceProvider.getStorageAdapter().storageAddNewCustomer(cid, set);

        //now we add a new tokenset to the initial tokenSet
        TokenSet newSet = new TokenSet();
        newSet.add(generator.generate());
        newSet.add(generator.generate());
        serviceProvider.getStorageAdapter().storageStoreTokens(cid, newSet);

        //now there should be a total of 3 tokens in the tokenSet
        assertEquals(3, serviceProvider.getStorageAdapter().storageCheckCustomerTokenSize(cid));
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
        serviceProvider.getStorageAdapter().storageAddNewCustomer(cid, set);

        //now we consume token2 and checks that it was removed
        serviceProvider.getStorageAdapter().storageConsumeToken(cid, token2);
        assertFalse(serviceProvider.getStorageAdapter().storageCheckCustomerToken(cid, token2));

    }

    @Test
    void storageAddNewCustomer() {
        String cid = "cid1";
        TokenSet set = new TokenSet();
        set.add(generator.generate());
        serviceProvider.getStorageAdapter().storageAddNewCustomer(cid, set);
        //call method from IStorageAdapter that returns a map or cid?
        assertTrue(serviceProvider.getStorageAdapter().isCustomerCreatedInStorage(cid));
    }
}