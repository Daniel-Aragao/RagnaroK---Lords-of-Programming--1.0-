package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Gráficos.MainFrame;

public class Screen_M extends JPanel {
		JPanel tela;
		JPanel menu;
		JButton play;
		JButton cartas;
		JButton sair;

		public Screen_M() {
			menu = new JPanel();
			menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
			menu.add(play = new JButton("Play"));
			menu.add(cartas = new JButton("Cartas"));
			menu.add(sair = new JButton("Sair"));
			Dimension d = new Dimension(MainFrame.WIDTH * (MainFrame.SCALE - 1), MainFrame.HEIGHT
					* (MainFrame.SCALE - 1));
			menu.setSize(d);
			menu.setBackground(Color.black);
			this.add(menu, "Center");
			play.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playClick();
				}
			});
		}

		public void playClick() {
			System.out.println("s");
			

		}
	}