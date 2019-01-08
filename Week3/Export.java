package Week3;

import edu.duke.*;
import org.apache.commons.csv.*;

/*
 * @author (Pratham Shroff) 
 * @version (23 December 2018)
 * TESTED
 */

/* Assignment
 * The CSV file exportdata.csv has information on the export products of countries;
 * you can download a .zip folder with this and other export data files here.
 * In particular it has three column headers labeled Country, Exports, and Value (dollars).
 * The Country column represents a country from the world, the Exports column is a list of export items for a country,
 * and the Value (dollars) column is the dollar amount in millions of their exports in the format of a dollar sign,
 * followed by an integer number with a comma separator every three digits from the right.
 * An example of such a value might be “$400,000,000”.
 */

public class Export {
    
    /* 1. This method is the main() or the tester method
     */
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String info = countryInfo(parser, "Nauru");
	System.out.println(info);
	parser = fr.getCSVParser();
	System.out.println("");
	
	listExportersTwoProducts(parser, "cotton", "flowers");
	parser = fr.getCSVParser();
	System.out.println("");
	
	int numOfCountries = numberOfExporters(parser, "cocoa");
	System.out.println(numOfCountries);
	parser = fr.getCSVParser();
	System.out.println("");
	
	bigExporters(parser, "$999,999,999,999");
    }
    
    /* 2. This method returns information on the about a country if it is found in the CSV file and returns "NOT FOUND" otherwise.
     */
    public String countryInfo(CSVParser parser, String country)
    {
        for (CSVRecord record : parser)
        {
            if( (record.get("Country")).contains(country))
            {
                String info = record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return info;
            }
        }
        return "NOT FOUND";
    }
    
    /* 3. This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
     */
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record : parser)
        {
            if( (record.get("Exports")).contains(exportItem1) && 
                (record.get("Exports")).contains(exportItem2)  )
            {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    /* 4. This method returns the number of countries that export exportItem.
     */
    public int numberOfExporters(CSVParser parser, String exportItem)
    {
        int counter = 0;
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if(exports.contains(exportItem))
            {
                counter++;
            }
        }
        return counter;
    }
    
    /* 5. This method prints the names of countries and their value amount for all countries whose Value (dollars) string is longer than the amount string.
     */
    public void bigExporters(CSVParser parser, String amount)
    {
        for (CSVRecord record : parser)
        {
            String value = record.get("Value (dollars)");
            String country = record.get("Country");
            if( value.length()  > amount.length() )
            {
                System.out.println(country + ": " + value );
            }
        }
    }
}
