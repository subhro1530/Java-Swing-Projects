package Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {
    // Constructor or methods to initialize the panel can be added here
	BufferedImage image;
    public Panel() {
    	try {
			image=ImageIO.read(new File("D:/Downloads/test.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void paintComponent(Graphics g) {
    	// g.drawImage(image,0,0,null);
    	
    	//	Making a rectangle
    	g.setColor(Color.RED);
    	g.fillRect(10, 10, 100, 50);
    }
    
}
