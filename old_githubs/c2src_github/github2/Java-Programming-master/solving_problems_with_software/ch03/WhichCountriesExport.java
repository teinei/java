/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Win Wu
 * @date 2015/12/07
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WhichCountriesExport {    
    public void listCountryInfo(CSVParser parser, String countryOfName)
    {
      for(CSVRecord record : parser){
        String country = record.get("Country");
        
        if (country.contains(countryOfName)){
           System.out.print(country + ": ");
           System.out.print(record.get("Exports") + " :");
           System.out.println(record.get("Value (dollars)"));
        } 
      }
    }
    
    public void whoExportsGermany()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listCountryInfo(parser, "Germany"); 
    }
    
    // 3.
    public void listExportersTwoProducts(CSVParser parser,  String exportItem1, String exportItem2)
    {
      for(CSVRecord record : parser)
      {
          String exports = record.get("Exports");
          
          if (exports.contains(exportItem2) && exports.contains(exportItem2))
          {
            System.out.println(record.get("Country")); 
          }
        
      }
    }
    
    public void whoExportInGoldAndDiamonds()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
    
    }
    
    
    // 4.
    public void numberOfExporters(CSVParser parser, String exportItem)
    {
      
      // defaut counter
      int count = 0;
      
      for(CSVRecord record: parser)
      {
         String exports = record.get("Exports");
         
         
         if(exports.contains(exportItem))
         {
           count++;   
         }
      }
      
      System.out.println(count);
    
    }
    
    
    public void testCountGolds()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        numberOfExporters(parser, "gold");
    }
    
    // 5.
    public void bigExporters(CSVParser parser, String amount)
    {
        for(CSVRecord record : parser)
        {
            String values = record.get("Value (dollars)");
            
            if (values.length() > amount.length())
            {
                System.out.print(record.get("Country") + " ");
                System.out.println(values);
            }
            
        }
    
    }
    
    public void testWhoValueLengthBigger()
    {
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       bigExporters(parser, "$999,999,999");
    }
    
    
}
