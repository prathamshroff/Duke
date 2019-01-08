package Week2.StringsFirstAssignments;


/**
 * Write a description of class Part1 here.
 *
 * @author (Pratham Shroff)
 * @version (12 December 2018)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int start=dna.indexOf("ATG");
        if (start==-1) return null;
        int stop=dna.indexOf("TAA",start);
        if (stop==-1) return null;
        if ((start-stop)%3!=0) return null;
        return dna.substring(start,stop+3);
    }
    public void testSimpleGene() {
        String a = "AAATGCCCTAACTAGATTAAGAAACC";
        String b = "CCAATGCAGCGATAC";
        String c = "CTAATCCGGATCCGA";
        String d = "CCAGCATGCCAGTCAGCTAACAG";
        String e = "CCAGCATGCCAGTAGCTAACAG";
        
        System.out.println("The string is: " + a + "\nThe Gene is: " + findSimpleGene(a));
        System.out.println("The string is: " + b + "\nThe Gene is: " + findSimpleGene(b));
        System.out.println("The string is: " + c + "\nThe Gene is: " + findSimpleGene(c));
        System.out.println("The string is: " + d + "\nThe Gene is: " + findSimpleGene(d));
        System.out.println("The string is: " + e + "\nThe Gene is: " + findSimpleGene(e));
    }
    public static void main (String[] args) {
        Part1 obj = new Part1();
        obj.testSimpleGene();
    }
}
