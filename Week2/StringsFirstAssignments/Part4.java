package StringsFirstAssignments;
import edu.duke.*;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 
{
    public void printURL(String url)
    {
        URLResource obj=new URLResource(url);
        for(String words:obj.words())
        {
            if(words.toLowerCase().indexOf("youtube.com")!=-1)
            {
                int firstIndex=words.indexOf("\"");
                int lastIndex=words.indexOf("\"",firstIndex+1);
                System.out.println(words.substring(firstIndex+1,lastIndex));
            }
        }
    }
    public void testURL()
    {
        printURL("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
    public static void main()
    {
        Part4 obj=new Part4();
        obj.testURL();
    }
}
