package Week4;

import edu.duke.*;
import java.io.*;

/**
 * Convert any number of images to a gray scale version by setting all color components of each pixel to the same value.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class GrayScaleConverter {
	//I started with the image I wanted (inImage)
	public ImageResource makeGray(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's red to average
			pixel.setRed(average);
			//set pixel's green to average
			pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
		}
		//outImage is your answer
		return outImage;
	}

	public void selectConvertSave () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource gray = makeGray(inImage);
			String fname = inImage.getFileName();
			String newName = "gray-" + fname;
			gray.draw();
			gray.setFileName(newName);
			gray.save();
		}
	}
	
	
	
	
	
	public ImageResource invert(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's red to average
			pixel.setRed(255 - inPixel.getRed());
			//set pixel's green to average
			pixel.setGreen(255 - inPixel.getGreen());;
			//set pixel's blue to average
			pixel.setBlue(255 - inPixel.getBlue());
		}
		//outImage is your answer
		return outImage;
	}

	public void selectInvertSave () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource inverted = invert(inImage);
			String fname = inImage.getFileName();
			String newName = "inverted-" + fname;
			inverted.draw();
			inverted.setFileName(newName);
			inverted.save();
		}
	}
}
