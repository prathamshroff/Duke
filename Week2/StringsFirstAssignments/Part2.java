package StringsFirstAssignments;

/**
 * Write a description of class Part2 here.
 *
 * @author (Pratham Shroff)
 * @version (12 December 2018)
 */
public class Part2
{
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        if(dna==dna.toUpperCase())
        {
            startCodon=startCodon.toUpperCase();
            stopCodon=stopCodon.toUpperCase();
        }
        else
        {
            startCodon=startCodon.toLowerCase();
            stopCodon=stopCodon.toLowerCase();
        }
        
        int start=dna.indexOf(startCodon);
        if (start==-1) return null;
        int stop=dna.indexOf(stopCodon,start);
        if (stop==-1) return null;
        if ((start-stop)%3!=0) return null;
        return dna.substring(start,stop+3);
    }
    public void testSimpleGene() {
        String a = "AAATGCCCTAACTAGATTAAGAAACC";
        String b = "CCAATGCAGCGATAC";
        String c = "CTAATCCGGATCCGA";
        String d = "ccagcatgccagtcagctaacag";
        String e = "CCAGCATGCCAGTAGCTAACAG";
        String startCodon="ATG";
        String stopCodon="TAA";
        
        System.out.println("The string is: " + a + "\nThe Gene is: " + findSimpleGene(a,startCodon,stopCodon));
        System.out.println("The string is: " + b + "\nThe Gene is: " + findSimpleGene(b,startCodon,stopCodon));
        System.out.println("The string is: " + c + "\nThe Gene is: " + findSimpleGene(c,startCodon,stopCodon));
        System.out.println("The string is: " + d + "\nThe Gene is: " + findSimpleGene(d,startCodon,stopCodon));
        System.out.println("The string is: " + e + "\nThe Gene is: " + findSimpleGene(e,startCodon,stopCodon));
    }
    public static void main (String[] args) {
        Part2 obj = new Part2();
        obj.testSimpleGene();
    }
}
