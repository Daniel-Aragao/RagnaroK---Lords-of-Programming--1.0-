package state;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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

public class RegrasState extends State{
	public static final BufferedImage BACKGROUND = Importar.getBackground(BackgroundID.RegrasBackground);
	
	JFrame mFrame;
	private ButtonsListener buttons;
	private RegrasPanel regrasPanel;
	
	public RegrasState(Game game) {
		mFrame = game.getFrame().getFrame();
		
		new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));
		
		regrasPanel = new RegrasPanel();
		regrasPanel.setButtons(new ButtonsListener() {
			
			@Override
			public void pressed(JButton button) {
				State.setState(new MenuState(game));				
			}
		});
		
		mFrame.setContentPane(regrasPanel);
		
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
			new MusicPlayer().start(Importar.getSound(BackgroundSoundID.menu));;
		}
	}

}
@SuppressWarnings("serial")
class RegrasPanel extends JPanel{
	
	private JTextPane textarea;
	private JButton menu;
	
	private ButtonsListener buttons;
	
	public RegrasPanel(){
		this.setLayout(new GridBagLayout());
		textarea = new JTextPane();
		textarea.setBackground(new Color(0,0,0,100));
		textarea.setForeground(Color.white);
		textarea.setEditable(false);
		
		String msg = "----------------------------------------------------------------------------\n";
		msg +=       "                        REGRAS                         \n";
		msg +=       "----------------------------------------------------------------------------\n\n";
		msg +=       "Cada jogador possui um deque inicial de 30 cartas, 10 cartas personagens, \n"
				   + "10 cartas mágicas, 5 cartas ED e 5 cartas OO.\n\n ";
		msg +=		 "Cartas monstro tem 3 propriedades, ataque, defesa e skill, que somam 15, \n"
				   + "podendo ultrapassar no caso do uso de cartas mágicas específicas. \n\n"
				   + "O jogo tem QUATRO tipos diferentes de cartas mágica, que podem \n"
				   + "atuar em cima do monstro na hora de seu ataque ou defesa, dependendo da carta.\n\n"
				   + "Cartas ED e OO são cartas únicas que só podem ser utilizadas uma vez para cada\n"
				   + "carta diferente, por exemplo a carta \" herança\" pode se repetir no baralho, \n"
				   + "mas só pode ser utilziada uma vez a cada partida. Existem 3 ED's e "
				   + "4 OO's diferentes\n OBS.: quando o jogador perde se o baralho chegar ao fim\n\n"
				   + "O jogo começa com cada jogador possuindo 150 pontos de energia e o player 1 \n"
				   + "puxando uma carta, se essa for criatura ela deve ser posta diretamente no \n"
				   + "campo ou recolocada no baralho, se não for criatura ela poderá ir para mão\n"
				   + "ou voltar para o baralho.\n O jogador só poderá atacar após o 3º turno jogado\n\n"
				   + "O jogo segue uma cronologia básica, o jogador deve puxar a carta, depois \n"
				   + "escolher quem atacar, se atacar poderá ativar suas Cartas ED/OO, também poderá\n"
				   + "passar a vez para o próximo jogador. Se ainda não tiver no 4º turno poderá \n"
				   + "ativar a carta ED ou a OO sem atacar.\n ";
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
		 
		 gr.drawImage(RegrasState.BACKGROUND,0,0,MainFrame.WIDTH*MainFrame.SCALE
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


