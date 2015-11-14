package state;
import Util.BackgroundID;
import Util.Importar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuState extends State{
	String imagemUrl = null;
	public BufferedImage image;
	
	public MenuState(){
//		imagemUrl = leituraArquivo.readLine();
//		imagem = ImageIO.read(new File(imagemUrl));
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void draw(Graphics g) {
		
		
	}

	@Override
	public void repaintComponents() {
		// TODO Auto-generated method stub
		
	}

}


















//		JPanel tela;
//		JPanel menu;
//		JButton play;
//		JButton cartas;
//		JButton sair;
//
//		public Screen_M() {
//			menu = new JPanel();
//			menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
//			menu.add(play = new JButton("Play"));
//			menu.add(cartas = new JButton("Cartas"));
//			menu.add(sair = new JButton("Sair"));
//			Dimension d = new Dimension(MainFrame.WIDTH * (MainFrame.SCALE - 1), MainFrame.HEIGHT
//					* (MainFrame.SCALE - 1));
//			menu.setSize(d);
//			menu.setBackground(Color.black);
//			this.add(menu, "Center");
//			play.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					playClick();
//				}
//			});
//		}
//
//		public void playClick() {
//			System.out.println("s");
//			
//
//		}