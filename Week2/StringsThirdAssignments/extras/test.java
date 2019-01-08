package StringsThirdAssignments.extras;
import edu.duke.*;

/**
 * Write a description of test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0) return currIndex;
            else currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
        return -1;
    }
    public String findGene (String dna, int where) {
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
    public StorageResource getAllGenes(String dna) {
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
    public void main(String dna)
    {
        StorageResource sr = getAllGenes(dna);
        for (String item : sr.data())
        {
            System.out.println(item);
        }
    }
    
}
