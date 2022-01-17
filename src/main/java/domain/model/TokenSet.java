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
     * Method for finding the index of a specified token
     * @param token which index we wish to find
     * @return the index of the specified token
     */
    public int getTokenIndex(String token){

        int indexOfToken = -1;
        for(int i = 0; i < 6 ; i++){

            if(set[i] != null){
                if(set[i].equals(token)){
                    indexOfToken = i;
                }
            }
        }
        return indexOfToken;
    }


    /**
     * Method that return a token specified by its index in the TokenSet
     * @param index of the token
     * @return token
     */
    public String getToken(int index){return set[index];}

    /**
     * Method for checking if a specified token exists int the set
     * @param token to search for
     * @return boolean stating if it was found or not
     */
    public boolean searchForToken(String token){
        for (String str: set){
            if(str != null){
                if(str.equals(token)){return true;}}
            }
        return false;
    }


    /**
     * Method for finding the number of tokens in a tokenSet
     * @return an integer representing the number of tokens in the tokenSet
     */
    public int numberOfTokens(){

        int count = 0;
        for (String token : set){
            if(token != null){
                count++;
            }
        }
        return count;
    }

    /**
     * Getter for the actual set
     * @return String array of tokens
     */
    public String[] getSet(){return set;}

}
