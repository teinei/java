
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
        
        
        // find how mans CTG
        int countCTG = 0;
        int demostartstr = 0;
        
        for(int startstr = demostartstr; startstr < dna.length(); startstr++)
        {   
            System.out.println("第幾個字開始看: " + startstr);
            System.out.println(dna.indexOf("ctg", startstr));
            
            if (startstr+3 <= dna.length()) {   
                String sub = dna.substring(startstr, startstr+3);
                System.out.println("sub 結果" + sub);
                
                if(sub.equals("ctg")){
                    countCTG++;
                    demostartstr = startstr+2;
                }
                
             }
        }
        System.out.println("countCTG :   " + countCTG);


        //-------------
        
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
    
    
    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " character");
            String result = findProtein(s);
            System.out.println("found:  "+ result);
        }
    }
}
