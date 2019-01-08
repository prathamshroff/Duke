package StringsThirdAssignments;
import edu.duke.*;

/**
 * Write a description of UsingStorageResource here.
 * 
 * @author (Pratham Shroff) 
 * @version (22 December 2018)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0) return currIndex;
            else currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
        return -1;
    }
    public void testfindStopCodon()
    {
        //            |  |  |  |  |  |  |  |  |
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        
        int dex = findStopCodon(dna, 0,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 9,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 1,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 0,"TAG");
        System.out.println(dex);
    }
    public String findGene (String dna, int where)
    {
        int startIndex = dna.indexOf("ATG", where);
	if(startIndex == -1) {
	    return "";
	}
	
	int taaIndex = findStopCodon(dna, startIndex, "TAA");
	int tagIndex = findStopCodon(dna, startIndex, "TAG");
	int tgaIndex = findStopCodon(dna, startIndex, "TGA");
	
	int minIndex = 0;
	if ( taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex) ) minIndex = tagIndex;
	else                                                             minIndex = taaIndex;
	
	if ( minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex) ) minIndex = tgaIndex;
	if ( minIndex == -1 ) return null;
	
	return dna.substring(startIndex, minIndex + 3);
    }
    public StorageResource getAllGenes(String dna)
    {
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while (true)
        {
            String gene = findGene(dna, startIndex);
            if (gene == "")
            {
                break;
            }
            
            else sr.add(gene);
            
            startIndex = (dna.indexOf(gene, startIndex)) + (gene.length());
            
        }
        return sr;
    }
    public double cgRatio (String dna)
    {
        double counter = 0.0;
        for (int i = 0; i < dna.length(); i++)
        {
            if ( dna.charAt(i) == 'C' || dna.charAt(i) == 'G' || dna.charAt(i) == 'c' || dna.charAt(i) == 'g' ) counter++;
        }
        return (counter/dna.length());
    }
    public int countCTG (String dna)
    {
        int startIndex = 0;
        int count = 0;
        
        int index = dna.indexOf ("CTG", startIndex);
        while (true)
        {
            if (count == -1 || count > dna.length()) break;
            count++;
            index = dna.indexOf("CTG", index+3);
        }
        
        return count;
    }
    public void processGenes(StorageResource sr)
    {
        int counter1 = 0, counter2 = 0, longestLength = 0, items = 0;
        
        // 9 characters
        System.out.println("Strings longer than 9 chars:");
        for (String item : sr.data())
        {
            if (item.length() > 60)
            {
                System.out.println(item);
                counter1++;
            }
        }
        //System.out.println("No. of Strings longer than 9 chars: " + counter1);
        
        // cg ratio and longest gene
        System.out.println("Strings that have CG ratio higher than 0.35: ");
        for (String item : sr.data())
        {
            if (cgRatio(item) > 0.35)
            {
                System.out.println(item);
                counter2++;
            }
            if (item.length() > longestLength)
            {
                longestLength = item.length();
            }
            items++;
        }
        System.out.println("No. of Genes: " + items);
        System.out.println("No. of Strings longer than 9 chars: " + counter1);
        System.out.println("No. of Strings that have CG ratio higher than 0.35: " + counter2);
        System.out.println("Length of the Longest Gene: " + longestLength);
    }
    public void testProcessGenes()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        //System.out.println(dna);
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
    }
}
