package Util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonCustomization {

	
	
	
	public static void buttonCustomization(JButton button){
		button.setForeground(Color.white);
		button.setBackground(new Color(62,28,100));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setFocusPainted(false);
	}
}
