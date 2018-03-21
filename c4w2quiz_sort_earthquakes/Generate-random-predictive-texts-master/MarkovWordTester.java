
/**
 * Write a description of MarkovWordTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWordTester {
    public void testMKIndexOf(){
		String[] source = {"this", "is", "good","a", "test","haha","hee"};
		String[] test = {"good","a","test"};
		WordGram testWg = new WordGram(test,0,3);
		MarkovWord mk2 = new MarkovWord(3);
		System.out.println(mk2.indexOf(source,testWg,0));
	}
}
