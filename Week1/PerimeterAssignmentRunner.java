package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count=0;   //counter  variable
        for(Point currPt:s.getPoints()) {
            count++;
        }    
        return count;
    }

    public double getAverageLength(Shape s) {
        double AverageLength=(getPerimeter(s))/(getNumPoints(s));
        return AverageLength;
    }

    public double getLargestSide(Shape s) {
        double largest=0.0;
        Point prevPt=s.getLastPoint();
        for (Point currPt:s.getPoints()) {
            double currDist=prevPt.distance(currPt);
            if (currDist>largest){
                largest=currDist;
            }
            prevPt=currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        double largestX=0.0;
        for (Point currPt:s.getPoints()) {
            double currX=currPt.getX();
            if (currX>largestX){
                largestX=currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim=0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shape=new Shape(fr);
            double currPerim=getPerimeter(shape);
            if (currPerim>largestPerim) {
                largestPerim=currPerim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim=0.0;
        File largest = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shape=new Shape(fr);
            double currPerim=getPerimeter(shape);
            if (currPerim>largestPerim) {
                largestPerim=currPerim;
                largest=f;
            }
        }
        return largest.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("Perimeter = " + length);
        int NumPoints = getNumPoints(s);
        System.out.println("Number of Points = " + NumPoints);
        double AverageLength = getAverageLength(s);
        System.out.println("Average Length = " + AverageLength);
        double LargestSide = getLargestSide(s);
        System.out.println("Largest Side = " + LargestSide);
        double LargestX = getLargestX(s);
        System.out.println("Largest X = " + LargestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double LargestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter = " + LargestPerim);
    }

    public void testFileWithLargestPerimeter() {
        String LargestFile = getFileWithLargestPerimeter();
        System.out.println("File with Largest Perimeter = " + LargestFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
