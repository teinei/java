
/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 * 
 * @author ShreyamDuttagupta
 * @version 1.0.1
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();//"errors.txt"
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    public void tester(){
        /*
           What is the most common word length 
           (ignoring the punctuation of the first and last character 
           of each group of characters)?
           */
        findUnique();
       // System.out.println("# unique words: "+myWords.size());
        int index = findMax();
        System.out.println("findMax() "+index);
        int sum = 0;
        String mostCommon="";
        int mostCommonIndex=0;
        //int maxIndex=0;
       System.out.println("max word/freq: "+myWords.get(index)+" "+
                           myFreqs.get(index));
       for(int i=0; i<myWords.size(); i++){
           int freq=myFreqs.get(i);           
           String word=myWords.get(i);
           if(freq>mostCommonIndex){
               mostCommonIndex=freq; 
               mostCommon=word;
            }
           System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
           sum++;
       } 
        System.out.println(sum + "unique");
        int l=mostCommon.length();
        System.out.println("mostCommen word length "+l);
    }
    public int findMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
