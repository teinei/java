
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovWord  implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order; 
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	public int indexOf(String[] words,WordGram target,int start){
	    for(int k=start;k<words.length-target.length();k++){
	        WordGram wordsWg = new WordGram(words,k,target.length());
	        if(wordsWg.equals(target)){
	            return k;
	        }
	    }
	    return -1;
	}
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		for(int i=0;i<myOrder;i++){
		    String current = myText[index+i];
		    sb.append(current);
		    sb.append(" ");
		}
		String startWords = sb.toString();
		String[] startWordsA = startWords.split("\\s+");
		WordGram startWg = new WordGram(startWordsA,0,startWordsA.length);
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(startWg);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			startWg = startWg.shiftAdd(next);
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(WordGram kGram) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while(pos <myText.length){
	        int start = indexOf(myText,kGram,pos);
	        if(start == -1){
	            break;
	        }
	        if(start+1>myText.length){
	            break;
	        }
	        String next = myText[start+kGram.length()];
	        follows.add(next);
	        pos = start +1;
	    }
	    return follows;
    }
}

