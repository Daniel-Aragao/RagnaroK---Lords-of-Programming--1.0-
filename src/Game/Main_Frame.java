package Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Input.Mouse;
import Util.Importar;

@SuppressWarnings("serial")
public class Main_Frame extends JFrame {
	public static final int WIDTH = 256;
	public static final int HEIGHT = 192;
	public static final int SCALE = 4;
	//1024 x 768
	Screen_G jogo;
	Screen_M menu;

	public Main_Frame() {
		super("Ragnarok - Lords of Programming(1.0)");
		Dimension d = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setSize(WIDTH * SCALE, HEIGHT * SCALE);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jogo = new Screen_G();
		jogo.start();
		jogo.setSize(WIDTH*3,HEIGHT*3);
		getContentPane().add("Center", jogo);
		
	}

	public void Menu_Principal() {
	}

	public void jogo() {
		getContentPane().add("Center", jogo);

	}

	public static void main(String[] args) {
	
		
		System.out.println("Start");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main_Frame().setVisible(true);
			}
		});

	}

	

}
