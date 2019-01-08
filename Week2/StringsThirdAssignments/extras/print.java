package StringsThirdAssignments.extras;
import edu.duke.*;

/**
 * Write a description of print here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class print {
    public void testProcessGenes()
    {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(dna);
    }
}
