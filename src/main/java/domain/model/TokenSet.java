package domain.model;

public class TokenSet {

    private String set[] = new String[6];

    public void add(String token){

        for(int i = 0; i < 6; i++){
            if(set[i].equals(null)){
                set[i] = token;
            }
        }
    }

}
