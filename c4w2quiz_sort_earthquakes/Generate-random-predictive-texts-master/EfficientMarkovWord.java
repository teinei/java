
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class EfficientMarkovWord implements IMarkovModel{

    private int myOrder;
    private String[] myText;
    private Random myRandom;
    private HashMap <WordGram ,ArrayList<String>> map;
    
    public EfficientMarkovWord(int n) {
        myRandom = new Random();
        myOrder = n;
        map = new HashMap<WordGram ,ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    public HashMap<WordGram ,ArrayList<String>> getMap (){
        return map;
    }
    private boolean contains(HashMap<WordGram ,ArrayList<String>> map,WordGram wg){
        for(WordGram mapWg : map.keySet()){
            if(mapWg.equals(wg)){
               return true;
            }
        }
        return false;
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
    public void buildMap(){
        //map = new HashMap<String ,ArrayList<String>>();
        //yes-this-is-a-thin-pretty-pink-thistle
        
        for(int i=0;i<myText.length;i++){
            if(i+myOrder<=myText.length){
                WordGram wg = new WordGram(myText,i,myOrder); 
                if(!contains(map,wg)){
                    map.put(wg,new ArrayList<String>());
                }
            }
        }
        
                /*ArrayList <String> followed = map.get(key);
                //System.out.println("The ArrayList before adding is "+followed);
                if(i + key.length()+1<myText.length()){
                   String next = myText.substring(i+key.length(),i+key.length()+1);
                   followed.add(next);
                   //System.out.println("The ArrayList after adding is "+followed);
                   map.put(key,followed);
                }
                    
                    /*while(pos <myText.length()){
                        int start = myText.indexOf(key,pos);
                        System.out.println("The start is "+start);
                        if(start == -1){
                            break;
                        }
                        if(start + key.length()>myText.length()-1){
                            break;
                        }
                        String next = myText.substring(start+key.length(),start+key.length()+1);
                        System.out.println("The letter will be added is "+next);
                        followed.add(next);
                        pos = start + 1;
                        map.put(key,followed);
                    }*/
                
            
        
    }
    public void printHashMapInfo(){
        
        System.out.println("Size : "+map.size());
        int currentMax = 0;
        WordGram max = null;
        for(WordGram wg : map.keySet()){
            if(map.get(wg).size()>currentMax){
                currentMax = map.get(wg).size();
                max = wg;
            }
        }
        if(map.size()<100){
            for(WordGram wg : map.keySet()){
                System.out.println(wg+" : "+map.get(wg));
            }
        }
        System.out.println("Size of the biggest ArrayList: "+currentMax);
        System.out.println("The key of the biggest ArrayList: "+max);
        System.out.println("The biggest ArrayList itself is : "+map.get(max));
    }
    public ArrayList <String> getFollows(WordGram wg){
        buildMap();
        ArrayList <String> follows = map.get(wg);
        int pos = 0;
        while(pos <myText.length){
            int start = indexOf(myText,wg,pos);
            if(start == -1){
                break;
            }
            if(start+1>myText.length){
                break;
            }
            String next = myText[start+wg.length()];
            follows.add(next);
            pos = start +1;
        }
        
        return follows;
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
    public String toString(){
        int n = myOrder;
        return "Efficient MarkovModel of order "+n;
    }
}



