/**
 * Finding a Gene in DNA.
 * find a web links
 * 
 * @author Win Wu
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class WebLink {
    public String testReadUrlString()
    {
        URLResource page = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String source = page.asString();
        String result = "";
        //int start = 0;
        
        System.out.println("START!!!!!!!!!!!!!!!!!!");
        
        for(int start = 1; start < source.length(); start ++)
        {
            
            int index = source.indexOf("http", start);
            if (index == -1) {
                break;
            }

            int firstQuote = index; // 找到 第一個  "
            int endQuote = source.indexOf("\"", firstQuote);
            
            String sub = source.substring(firstQuote, endQuote);
            sub = sub.toLowerCase();

            // test 
            //System.out.println(sub);
            //System.out.println("--\n###");
         
            int withYoutube = sub.indexOf("youtube.com");
            
            if (withYoutube > -1) {
                System.out.println("sub :::; " + sub);
                System.out.println("\n");
                result = result +" \n " +sub;
            }
            start = endQuote + 1;
        }
        
        System.out.println("END!!!!!!!!!!!!!!!!!!");
        return result;
    }   

    public void testReadUrl(){
        URLResource page = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String source = page.asString();
        System.out.println(source);
   
    }
}
