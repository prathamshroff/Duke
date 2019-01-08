package StringsFirstAssignments;


/**
 * Write a description of class Part3 here.
 *
 * @author (Pratham Shroff)
 * @version (12 Dece)
 */
public class Part3
{
    public boolean twoOccurrences(String stringa, String stringb)
    {
       int index1=stringb.indexOf(stringa);
       if(index1==-1)return false;
       int index2=stringb.indexOf(stringa, index1+1);
       if(index2==-1)return false;
       return true;
    }
    public void testing()
    {
        String t1="by";     String t2="A story by Abby Long";
        String f1="atg";    String f2="ctgtatgta";
        
        System.out.println("Strings: "+t1+", "+t2+ "\nResult: "+twoOccurrences(t1,t2));
        System.out.println("Strings: "+f1+", "+f2+ "\nResult: "+twoOccurrences(f1,f2));
        
        t1="an";        t2="banana";
        f1="zoo";       f2="forest";
        
        System.out.println("\n\nThe part of the string after "+t1+" in "+t2+ " is "+lastPart(t1,t2));
        System.out.println("The part of the string after "+f1+" in "+f2+ " is "+lastPart(f1,f2));
    }
    public String lastPart(String stringa, String stringb)
    {
        int index = stringb.indexOf(stringa);
        if(index==-1) return stringb;
        return stringb.substring(index+stringa.length());
    }
    public static void main (String[] args) {
        Part3 obj = new Part3();
        obj.testing();
    }
}
