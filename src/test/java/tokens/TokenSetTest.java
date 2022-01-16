package tokens;

import domain.TokenGenerator;
import domain.model.TokenSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenSetTest {


    TokenGenerator generator = new TokenGenerator();
    TokenSet set;

    @Test
    void add() {
        set = new TokenSet();
        String token = generator.generate();
        set.add(token);
        assertEquals(token, set.getToken());
    }

    @Test
    void getToken(){
        set = new TokenSet();
        String token = generator.generate();
        set.add(token);
        assertNotNull(set.getToken());
    }

    @Test
    void remove(){
        set = new TokenSet();
        String token = generator.generate();
        set.add(token);
        set.remove(0);
        assertNull(set.getToken(0));

    }
}