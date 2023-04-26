package ie.tudublin;

public class Follow {
    private String word;
    private int count;

    public Follow(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount(){
        count += 1;
    }

    @Override
    public String toString() {
        return word + "(" + count + ")";
    }
}
