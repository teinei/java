/**
 * Assignment 2: Find All URLs
 * Write a program to find all the URLs on a web page and to
 * print the URLs,
 * print the number of URLs found,
 * print the the number of secure links found,
 * print the number of links that have “.com” in them,
 * print the number of links that end with “.com” or “.com/” and
 * print the total number of “.” that appear in all the links.
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
    public void testReadUrlString()
    {
        URLResource page = new URLResource("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        String source = page.asString();
        String result = "";
        
        // 找出有幾個 url
        int urlCount = 0;

        // 找出有幾個 https
        int urlWithSecureLinks = 0;
        
        // 幾個 url 有 .com
        int urlWithDotCom = 0;
        
        // 幾個 url 用 .com 結尾或是 .com/ 結尾
        int urlEndsWithCom = 0;
        
        
        int urlWithDoc = 0;
        
        System.out.println("START!!!!!!!!!!!!!!!!!!");
        
        for(int start = 1; start < source.length(); start ++)
        {
            System.out.println("URL START!");
            int index = source.indexOf("href=\"", start);
            if (index == -1) {
                break;
            }   

            // 更新 url 數量
            urlCount++;
            
            int firstQuote = index+6; // 找到 第一個  "
            int endQuote = source.indexOf("\"", firstQuote);
            
            String sub = source.substring(firstQuote, endQuote);
            // 先把整個 Url 轉小寫
            sub = sub.toLowerCase();
            
            // test 
            System.out.println(sub);
            System.out.println("--\n###");

            // 判斷有無https
            int withSecure = sub.indexOf("https");
            if (withSecure > -1) {
                urlWithSecureLinks++;
            }
            
            // 判斷有沒有.com
            int withDotCom = sub.indexOf(".com");
            if (withDotCom > -1)
            {
                urlWithDotCom++;
            }
            
            // 判斷是不是 .com 或是.com/ 結尾
            if (sub.endsWith(".com") || sub.endsWith(".com/")){
                urlEndsWithCom++;
            }
            
            int countDot = sub.length() - sub.replace(".", "").length();
            urlWithDoc = urlWithDoc + countDot;
         
            
            result = result +" \n " +sub;
        
            start = endQuote + 1;
            
            System.out.println("       URL END!");
        }
        
        System.out.println("END!!!!!!!!!!!!!!!!!!");
        System.out.println("結果");
        
        //return result;
        System.out.println(result);
        
        System.out.println("----------urlCount 錯-------------");
        System.out.println(urlCount);
        System.out.println("-----------urlWithSecureLinks 對------------");
        System.out.println(urlWithSecureLinks);
        System.out.println("----------urlWithDotCom 對-------------");
        System.out.println(urlWithDotCom);
        System.out.println("----------urlEndsWithCom- 對------------");
        System.out.println(urlEndsWithCom); 
        System.out.println("----------urlWithDoc-錯------------");
        System.out.println(urlWithDoc);
    }
}
