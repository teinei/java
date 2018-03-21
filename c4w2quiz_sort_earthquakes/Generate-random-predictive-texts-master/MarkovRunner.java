
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st,120 ,844); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    public void testHashMap(){
        FileResource fr = new FileResource();
		String st = fr.asString();
		String mytext = st.replace('\n', ' ');
	    EfficientMarkovWord em2 = new EfficientMarkovWord(2);
	    //String mytext = "this is a test yes this is really a test yes a test this is wow";
	    int size = 50;
	    em2.setRandom(65);
	    em2.setTraining(mytext);
	    em2.buildMap();
	    HashMap<WordGram ,ArrayList<String>> map = em2.getMap();
	    for(WordGram wg : map.keySet()){
	        ArrayList<String> current = em2.getFollows(wg);
	        map.put(wg,current);
	    }
	    em2.printHashMapInfo();
    }
    public void compareMethods (){
	    FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
	    int size = 100;
	    EfficientMarkovWord em2 = new EfficientMarkovWord(2);
	    em2.setRandom(42);
	    MarkovWord m2= new MarkovWord(2);
	    m2.setRandom(42);
	    //System.nanoTime();
	    runModel(em2, st, size,42);
	    //System.nanoTime();
	    runModel(m2, st, size,42);
	    
    }
}
