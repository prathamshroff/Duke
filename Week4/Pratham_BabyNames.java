package Week4;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

/**
 * Write a description of BabyNames here.
 * 
 * @author (Pratham Shroff) 
 * @version (24 December 2018)
 */
public class Pratham_BabyNames {
    public void printNames()
    {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn >=100)
            {
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
            }
        }
    }
    /*
     * This method returns the total number of births (males and females) in a file
     * and the total number of names (males and females)
     */
    public void totalBirths(FileResource fr)
    {
        int totalBirths = 0, totalBirthsMale = 0, totalBirthsFemale = 0;
        int totalNames = 0, totalNamesMale = 0, totalNamesFemale = 0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (rec.get(1).equals("M"))
            {
                totalBirthsMale += numBorn;
                totalNamesMale += 1;
            }
            else
            {
                totalBirthsFemale += numBorn;
                totalNamesFemale += 1;
            }
        }
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Births (Female) = " + totalBirthsFemale);
        System.out.println("Total Births (Male) = " + totalBirthsMale);
        
        System.out.println("Total Names = " + totalNames);
        System.out.println("Total Names (Female) = " + totalNamesFemale);
        System.out.println("Total Names (Male) = " + totalNamesMale);
    }
    public void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    /*
     * This method returns the rank of the name in the file for the given gender, 
     * where rank 1 is the name with the largest number of births. 
     * If the name is not in the file, then -1 is returned.
     */
    public int getRank(int year, String name, String gender)
    {
        // testing
        // String fname = ("us_babynames/us_babynames_test/yob" + year + "short.csv");
        String fname = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fname);
        
        /*
         * long rank = -1;
         * CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord rec : parser)
        {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if(currGender.equals(gender) && currName.equals(name))
            {
                rank = rec.getRecordNumber();
            }
        }
        return rank;*/
        
        String searchName = null;
        int searchYear = 0;
        String searchGender = null;
        int rankFM = 0;
        int rankM = 0;
        int ranks = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            searchName = rec.get(0);
            searchGender = rec.get(1);
            
            
            if(searchGender.equals("F")){
                rankFM++;
                if(searchName.equals(name) && searchGender.equals(gender)){
                    ranks = rankFM;
                    break;
                } 
            } 
            else if (searchGender.equals("M")){
                rankM++;
                if(searchName.equals(name) && searchGender.equals(gender)){
                     ranks = rankM;
                     break;
                }
                else ranks = -1;
            }
        } 
        return ranks;
    }
    public void testGetRank()
    {
        int rank = getRank(2012, "Noah", "M");
        System.out.println("Rank is: " + rank);
    }
    public String getName(int year, int rank, String gender)
    {
        // String fname = ("us_babynames/us_babynames_test/yob" + year + "short.csv");
        String fname = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        FileResource fr = new FileResource(fname);
        
        String searchName = null;
        String searchGender = null;
        int rankFM = 0;
        int rankM = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            searchGender = rec.get(1);
            
            if(searchGender.equals("F")){
                rankFM++;
                if(rankFM == rank && searchGender.equals(gender)){
                    searchName = rec.get(0);
                    break;
                }
            } 
            else if (searchGender.equals("M")){
                rankM++;
                if(rankM == rank && searchGender.equals(gender)){
                     searchName = rec.get(0);
                     break;
                }
                else searchName = "NO NAME";
            }
        } 
        return searchName;
    }
    public void testGetName()
    {
        String name = getName(2012, 100, "M");
        System.out.println("Name is: " + name);
    }
    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear );
    } 
    public int yearOfHighestRank(String name, String gender)
    {
        int smallestRankSoFar = 0;
        int smallestYearSoFar = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            
            //            0123456789012345
            // example = "yob2012short.csv"
            int currYear = Integer.parseInt(f.getName().substring(3,7));
            int currRank = getRank(currYear, name, gender);
            
            if (smallestRankSoFar == 0)
            {
                smallestRankSoFar = currRank;
                smallestYearSoFar = currYear;
            }
            else if (currRank != -1)
            {
                if (currRank < smallestRankSoFar)
                {
                    smallestRankSoFar = currRank;
                    smallestYearSoFar = currYear;
                }
            }
        }
        if (smallestRankSoFar == -1) smallestYearSoFar = -1;
        return smallestYearSoFar;                         
    }
    public void testYearOfHighestRank()
    {
        int year = yearOfHighestRank("Mich", "M");
        System.out.println(year);
    }
    public double getAverageRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int sum = 0, count = 0;
        for (File f : dr.   selectedFiles())
        {
            FileResource fr = new FileResource(f);
            int currYear = Integer.parseInt(f.getName().substring(3,7));
            int currRank = getRank(currYear, name, gender);
            if (currRank != -1)
            {
                sum += currRank;
                count++;
            }
        }
        if (sum == 0) return (-1.0);
        else return ( ((double)sum)/count );
    }
    public void testGetAverageRank()
    {
        double avgRank = getAverageRank("Jacobi", "M");
        System.out.println(avgRank);
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        String fname = ("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        // String fname = ("us_babynames/us_babynames_test/yob" + year + "short.csv");
        
        FileResource fr = new FileResource(fname);
        int rank = getRank(year, name, gender);
        
        int totalNamesFemale = 0, numBorn = 0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if (rec.get(1).equals("F"))
            {
                totalNamesFemale += 1;
            }
        }

        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int currBorn = Integer.parseInt(rec.get(2));
            String currGender = rec.get(1);
            long currRank = rec.getRecordNumber();
            
            if (currGender.equals("F"))
            {
                if(gender.equals(currGender) && rank > currRank)
                {
                    numBorn += currBorn;
                }
            }
            else
            {
                currRank = currRank - totalNamesFemale;
                if(gender.equals(currGender) && rank > currRank)
                {
                    numBorn += currBorn;
                }
            }
        }
        return numBorn;
    }
    public void testGetTotalBirthsRankedHigher()
    {
        int totalBirths = getTotalBirthsRankedHigher(2012, "Sophia", "F");
        System.out.println(totalBirths);
    }
    public void testAll()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
        
        
        System.out.println("Rank from getRank = " + getRank(1971, "Frank", "M"));
        
        System.out.println("Name from getName = " + getName(1982, 450, "M"));
        
        whatIsNameInYear("Owen", 1974, 2014, "M");

        System.out.println(yearOfHighestRank("Mich", "M"));
        
        System.out.println(getAverageRank("Robert", "M"));
        
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
        

    }
    public static void main()
    {
        Pratham_BabyNames obj = new Pratham_BabyNames();
        obj.testAll();
    }
}