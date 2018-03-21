import edu.duke.*;
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        if(myWords.length>0){
            return myWords.length;
        }
        return 0;
    }

    public String toString(){
        String ret = "";
        StringBuilder Sb = new StringBuilder(ret);
        for(int i=0;i<myWords.length;i++){
            Sb.append(myWords[i]);
            Sb.append(" ");
        }
        ret = Sb.toString();
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length()!= other.length()){
            return false;
        }
        for(int k=0;k<myWords.length;k++){
            if(!myWords[k].equals(other.wordAt(k))){
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        
        String[] newMyWords = new String[myWords.length];
        for(int i=0;i<myWords.length-1;i++){
            String re = myWords[i+1];
            newMyWords[i] = re;
        }
        newMyWords[newMyWords.length-1] = word;
        out = new WordGram(newMyWords, 0, newMyWords.length);
        // you lose the first word
        // TODO: Complete this method
        return out;
    }
    public int hashCode(){
        String text = myWords.toString();
        return text.hashCode();
    }
}