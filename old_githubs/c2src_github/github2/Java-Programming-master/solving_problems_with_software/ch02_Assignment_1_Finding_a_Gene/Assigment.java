
/**
 * Write a description of TagFinder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class Assigment {
    // 回傳string
    public String findProtein(String dna)
    {
        // 先轉小寫比較
        dna = dna.toLowerCase();
        //System.out.println("lowercase dna: " + dna);
        int start = dna.indexOf("atg");
        if (start == -1)
        {
            // 連atg 都沒有byebye!
            return "";
        }
        int stop = dna.indexOf("tag", start+3);
        //TAG
        if ((stop - start) %3 == 0) {
            dna = dna.toUpperCase();
            return dna.substring(start, stop+3);
        }
        
        // TGA
        stop = dna.indexOf("tga", start+3);
        if ((stop - start) %3 == 0) {
            dna = dna.toUpperCase();
            return dna.substring(start, stop+3);
        }
        
        stop = dna.indexOf("taa", start+3);
        if ((stop - start) %3 == 0) {
            dna = dna.toUpperCase();
            return dna.substring(start, stop+3);
        }
      
        return "";
        
        
    }
    
    public void testAssigment1()
    {
        String testing = "AATGCTAGTTTAAATCTGA";
        String result = findProtein(testing);
        System.out.println(result);
   
        
    }
    
    /*public void testing()
    {
          
        String a ="ATGCCCTAG";
        String ap = "ATGCCCTAG";
        
        String result = findProtein(a);
        System.out.println("result" + result);
        System.out.println("ewfwef");
        if (ap.equals(result)){
            System.out.println("success for " + ap + " length " + ap.length());
        } else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
            
        }
    }
    
    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " character");
            String result = findProtein(s);
            System.out.println("found "+ result);
        
        }
    }
    */
}
