package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Images.sources;

public class ImageResize {
	Image dimg;
     public ImageResize(int x,int y,String s) {
    	BufferedImage img = null;
 		try {
 		    img = ImageIO.read(new File(s));
 		} catch (IOException e) {
 		    e.printStackTrace();
 		}
 		dimg = img.getScaledInstance(x,y,Image.SCALE_SMOOTH);
     }
     public static void main(String[]arges) {
    	 sources source = new sources();
			ImageResize resize2 = new ImageResize(300,400,source.getArcher()[1]);
  
     }
}
