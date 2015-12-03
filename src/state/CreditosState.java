package state;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Game.Game;
import Gráficos.MainFrame;
import Util.BackgroundID;
import Util.BackgroundSoundID;
import Util.ButtonCustomization;
import Util.Importar;
import Util.MusicPlayer;

public class CreditosState extends State{

	public static final Image BACKGROUND = Importar.getBackground(BackgroundID.creditos);
	JFrame mFrame;
	static Game game;
	private CreditosPane creditosPane; 

	ButtonsListener buttons;
	
	public CreditosState(Game game){
		this.game = game;
		mFrame = game.getFrame().getFrame();

		
		new MusicPlayer().start(Importar.getSound(BackgroundSoundID.game));
		
		creditosPane = new CreditosPane();
		creditosPane.setButtons(new ButtonsListener() {
			
			@Override
			public void pressed(JButton button) {
				State.setState(new MenuState(game));				
			}
		});
		
		mFrame.setContentPane(creditosPane);	
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void repaintComponents() {
		for(Component i: mFrame.getComponents()){
			i.revalidate();
			i.repaint();
		}
		if(!MusicPlayer.isAlive()){
			new MusicPlayer().start(Importar.getSound(BackgroundSoundID.game));;
		}
	}

}

@SuppressWarnings("serial")
class CreditosPane extends JPanel{
	private JTextPane textarea;
	private JButton menu;
	

	private ButtonsListener buttons;
	
	public CreditosPane(){
		this.setLayout(new GridBagLayout());
		textarea = new JTextPane();
		textarea.setBackground(new Color(0,0,0,57));
		textarea.setForeground(Color.white);
		textarea.setEditable(false);
		
		String msg = "----------------------------------------------------------------------------\n";
		msg +=       "                       CRÉDITOS                        \n";
		msg +=       "----------------------------------------------------------------------------\n\n";
		msg +=       "            DEV: DANIEL ARAGÃO ABREU FILHO             \n";
		msg +=       "                 PROF.HERLESON PONTES                  \n\n\n";
		
		
		msg +=       "----------------------------------------------------------------------------\n\n";
		msg +=       "                        FONTES:                         \n";
		msg +=       "               CONTEÚDO: RAGNAROK ONLINE               \n\n";
		msg +=       "                     REFÊRENCIAS:                       \n";
		msg +=       "                      CODENMORE                         \n";
		msg +=       "       MASTERING JAVA SWING - PART 1 JOHN PURCELL       \n";
		msg +=       "----------------------------------------------------------------------------\n\n";
		msg +=       "                    AGRADECIMENTOS:                     \n";
		msg +=       " AGRADEÇO PRIMEIRAMENTE A DEUS POR SEMPRE ME DA FORÇAS  \n"
				+    "                    PARA CONTINUAR.                     \n\n";
		msg +=       "     A FAMÍLIA POR ME EDUCAR E ME PROTEGER DO MUNDO     \n";
		msg +=       "        AOS MEUS AMIGOS QUE SEMPRE ME APOIARAM.         \n";
		msg +=       "----------------------------------------------------------------------------\n\n";
		
		StyledDocument doc = textarea.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		textarea.setText(msg);
		
		menu = new JButton("Menu");
		menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buttons.pressed((JButton) e.getSource());
				
			}
		});
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.NONE;
		gc.weightx = 1;
		
		gc.weighty = 0.3;
		gc.gridx = 0;
		gc.gridy = 0;
//		gc.anchor = GridBagConstraints .NORTH;
		this.add(textarea,gc);
		
//		gc.weighty = 0.;
		gc.gridy = 1;
		gc.weightx = 2;
		gc.anchor = GridBagConstraints .NORTH;
		ButtonCustomization.buttonCustomization(menu);
		this.add(menu,gc);
	}
	
	@Override
	public void paintComponent(final Graphics g){
		super.paintComponent(g);
		 Graphics gr = g.create();  
		 
		 gr.drawImage(CreditosState.BACKGROUND,0,0,MainFrame.WIDTH*MainFrame.SCALE
				 ,MainFrame.HEIGHT*MainFrame.SCALE,null);		 
		
		gr.dispose();
	}
	
	public ButtonsListener getButtons() {
		return buttons;
	}

	public void setButtons(ButtonsListener buttons) {
		this.buttons = buttons;
	}

	 
}
