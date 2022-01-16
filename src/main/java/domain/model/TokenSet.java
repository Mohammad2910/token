package domain.model;

/**
 * Class that represents a set of tokens.
 * @author Renjue and David
 */
public class TokenSet {

    private String set[] = new String[6];

    /**
     * Method for adding a token to the set
     * @param token to be added
     */
    public void add(String token){
        for(int i = 0; i < 6; i++){
            if(set[i] == null){
                set[i] = token;
                break;}
        }
    }

    /**
     * Method for removing a specified token from the set
     * @param token the token to remove from the set
     */
    public void remove(String token){
       for(int i = 0; i < 6; i++){
           if(set[i].equals(token)){
               set[i] = null;
               break;
           }
       }
    }

    /**
     * Method for retrieving a token from the set
     * @param index of the token to get
     * @return the token
     */
    public String getToken(int index){return set[index];} //todo maybe we should get the token by token itself and not index

    /**
     * Method for checking if a specified token exists int the set
     * @param token to search for
     * @return boolean stating if it was found or not
     */
    public boolean searchForToken(String token){
        for (String str: set){if(str.equals(token)){return true;}}
        return false;
    }

}
