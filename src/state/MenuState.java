package state;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.Game;
import Gráficos.MainFrame;
import Util.BackgroundID;
import Util.BackgroundSoundID;
import Util.ButtonCustomization;
import Util.Importar;
import Util.MusicPlayer;

public class MenuState extends State{
	
	JFrame mFrame;
	static Game game;
	MenuPanel menuPanel;
	ButtonsListener buttons;
	
	public MenuState(Game game){
		MenuState.game = game;
		mFrame = game.getFrame().getFrame();
		
		new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));
		
		menuPanel = new MenuPanel(); 
		menuPanel.setButtonsListener(new ButtonsListener() {
			
			@Override
			public void pressed(JButton button) {
				if(button.getText() == "Jogar"){
					setState(new GameState(game));
				}else if(button.getText() == "Regras"){
					setState(new RegrasState(game));    			 
				}else if(button.getText() == "Créditos"){
					setState(new CreditosState(game));
				}
				
			}
		});
		
		mFrame.setContentPane(menuPanel);
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void repaintComponents() {
		menuPanel.repaintComponents();
		for(Component i: mFrame.getComponents()){
			i.revalidate();
			i.repaint();
		}
		if(!MusicPlayer.isAlive()){
			new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));;
		}
	}

}

@SuppressWarnings("serial")
class MenuPanel extends JPanel{
	public static final BufferedImage BACKGROUND = Importar.getBackground(BackgroundID.InicioBackground);
	
	private ButtonsListener buttonsListener;
	private JButton jogar;
	private JButton regras;
	private JButton creditos;
	
	public MenuPanel(){
		jogar    = new JButton("Jogar"); 
		regras   = new JButton("Regras");  
		creditos = new JButton("Créditos");
		
		ButtonCustomization.buttonCustomization(jogar);
		ButtonCustomization.buttonCustomization(regras);
		ButtonCustomization.buttonCustomization(creditos);	
		
		jogar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonsListener.pressed((JButton) e.getSource());
				
			}
		});
		regras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonsListener.pressed((JButton) e.getSource());
				
			}
		});
		creditos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonsListener.pressed((JButton) e.getSource());
				
			}
		});
		
		this.setLayout(null);
		jogar.setBounds(800,200,150,40);
		regras.setBounds(800,280,150,40);
		creditos.setBounds(800,360,150,40);// x800 y200
		add(jogar);
		add(regras);
		add(creditos);		
	}
	
		
	public void repaintComponents() {
		for(Component i: this.getComponents()){
			i.revalidate();
			i.repaint();
		}		
	}	
	
	@Override
	public void paintComponent(final Graphics g){
		super.paintComponent(g);
		 Graphics gr = g.create();  
		 
		 gr.drawImage(MenuPanel.BACKGROUND,0,0,MainFrame.WIDTH*MainFrame.SCALE
				 ,MainFrame.HEIGHT*MainFrame.SCALE,null);		 
		
		gr.dispose();
	}


	public ButtonsListener getButtonsListener() {
		return buttonsListener;
	}


	public void setButtonsListener(ButtonsListener buttonsListener) {
		this.buttonsListener = buttonsListener;
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