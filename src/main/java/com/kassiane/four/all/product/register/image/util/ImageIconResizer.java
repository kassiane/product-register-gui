package com.kassiane.four.all.product.register.image.util;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageIconResizer {

	public ImageIcon imageToImageIconResized(Image image, int height){
		Icon imageIcon = new ImageIcon(image);
		
		float proportion = (float) imageIcon.getIconHeight() / (float) imageIcon.getIconWidth();
		int newWidth = (int) (height / proportion);
		
		Image newimg = image.getScaledInstance(newWidth, height,  java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(newimg);
		
	}
	
	public ImageIcon resizeImageIcon(ImageIcon image, int height){
		float proportion = (float) image.getIconHeight() / (float) image.getIconWidth();
		int newWidth = (int) (height / proportion);
		
		Image newimg = image.getImage().getScaledInstance(newWidth, height,  java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(newimg);
		
	}
}
