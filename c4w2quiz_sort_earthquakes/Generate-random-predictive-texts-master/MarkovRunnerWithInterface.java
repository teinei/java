
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,12);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,12);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,12);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,12);

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
		st = st.replace('\n', ' ');
	    EfficientMarkovModel em2 = new EfficientMarkovModel(6);
	    //String mytext = "yes-this-is-a-thin-pretty-pink-thistle";
	    int size = 50;
	    em2.setRandom(792);
	    em2.setTraining(st);
	    em2.buildMap();
	    em2.printHashMapInfo();
	}
	public void compareMethods(){
	    FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
	    int size = 1000;
	    EfficientMarkovModel em2 = new EfficientMarkovModel(2);
	    em2.setRandom(42);
	    MarkovModel m2= new MarkovModel(2);
	    m2.setRandom(42);
	    System.nanoTime();
	    runModel(em2, st, size,42);
	    System.nanoTime();
	    runModel(m2, st, size,42);
	    
	}
}
