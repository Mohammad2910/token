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
        assertEquals(token, set.getToken(0));
    }

    @Test
    void getToken(){
        set = new TokenSet();
        String token = generator.generate();
        set.add(token);
        assertNotNull(set.getToken(0));
    }

    @Test
    void remove(){
        set = new TokenSet();
        String token = generator.generate();
        set.add(token);
        set.remove(token);
        assertNull(set.getToken(0));

    }

    @Test
    void searchForToken(){
        String token = "str2";
        set = new TokenSet();
        set.add("str1");
        set.add(token);
        set.add("str3");
        assertTrue(set.searchForToken(token));
    }

    @Test
    void numberOfTokens(){
        set = new TokenSet();
        set.add("str1");
        set.add("str2");
        set.add("str3");
        assertEquals(3, set.numberOfTokens());
    }

    @Test
    void getTokenIndex(){
        set = new TokenSet();
        set.add("str1");
        set.add("str2");
        set.add("str3");
        assertEquals(2, set.getTokenIndex("str3"));
    }
}