package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> follows;
    
    public Word(String word) {
        this.word = word;
        this.follows = new ArrayList<>();
    }
    
    public String getWord() {
        return word;
    }
    
    public ArrayList<Follow> getFollows() {
        return follows;
    }
    
    public void addFollow(Follow follow) {
        follows.add(follow);
    }
    
    public Follow findFollow(String word) {
        for (Follow follow : follows) {
            if (follow.getWord().equals(word)) {
                return follow;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word).append(": ");
        for (Follow follow : follows) {
            sb.append(follow).append(" ");
        }
        return sb.toString().trim();
    }
}
