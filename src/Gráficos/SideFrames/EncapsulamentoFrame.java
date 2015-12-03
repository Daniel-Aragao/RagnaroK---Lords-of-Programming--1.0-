package Gráficos.SideFrames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import listeners.CartaClickedListener;
import tabuleiro.Jogador;
import tabuleiro.LogPanel;
import tabuleiro.Tabuleiro;
import Util.BackgroundID;
import Util.Importar;
import Util.Lista_de_Generics;
import entity.Carta;
import entity.Entity;

public class EncapsulamentoFrame {
	private static final int SCALE = 1;
	public static final int WIDTH = 600 * EncapsulamentoFrame.SCALE;
	public static final int HEIGHT = 200 * EncapsulamentoFrame.SCALE;
	
	public static final Dimension DEFAULT_SELECT_DIMENSION = 
			new Dimension(EncapsulamentoFrame.WIDTH, EncapsulamentoFrame.HEIGHT); 
	
	
	private JFrame frame;
	private EncapsulamentoPanel panel;

	public EncapsulamentoFrame(Lista_de_Generics<Carta> elementos , Jogador jogador, Jogador inimigo) {
		frame = new JFrame("Clique na carta que deseja remover");
		frame.setPreferredSize(EncapsulamentoFrame.DEFAULT_SELECT_DIMENSION);
		frame.setLocationRelativeTo(Tabuleiro.logPanel);		
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setFocusable(true);
		frame.setVisible(true);
		frame.requestFocus();
		frame.setAlwaysOnTop(true);
		
		BufferedImage icon = Importar.getBackground(BackgroundID.icone);
		frame.setIconImage(icon);
		
		panel = new EncapsulamentoPanel(elementos, jogador, inimigo, frame);
		frame.setContentPane(panel);
		
		frame.pack();
		
		BufferStrategy bs = frame.getBufferStrategy();
		if(bs == null){
			frame.createBufferStrategy(3);
			return;
		}
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public EncapsulamentoPanel getPanel() {
		return panel;
	}

}


@SuppressWarnings("serial")
class EncapsulamentoPanel extends JPanel{
	private BufferedImage imagem;
	private Carta selected;
	private JButton seleção;
		
	public EncapsulamentoPanel(Lista_de_Generics<Carta> elementos, Jogador jogador, Jogador inimigo, JFrame frame){
		this.setLayout(new FlowLayout(FlowLayout.LEADING,15,5));
		imagem = Importar.getBackground(BackgroundID.bafome);
		
		for(int i = 0; i < elementos.getQtdElementos(); i++){
			Carta aux = elementos.getElemento(i);
			aux.addCartaClickedListener(new CartaClickedListener() {
				
				@Override
				public void CardHoover(Carta c, boolean b) {
					int acr = 10;
					if(b){
						c.fantasy_CARTA_WIDTH += acr;
						c.fantasy_CARTA_HEIGHT += acr;
									
						jogador.getCommandListener().hooverInfo(c);
						
					}else{
						c.fantasy_CARTA_WIDTH -= acr;
						c.fantasy_CARTA_HEIGHT -= acr;
						
					}
					
				
					
				}
				
				@Override
				public void CardClicked(Entity entidade) {
					if(entidade != null && selected != entidade){
						
						
						TitledBorder border = BorderFactory.createTitledBorder("SELECIONADO");
						border.setTitleColor(new Color(255,0,00));
						border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
						entidade.setBorder(border);
					}
					if(selected != null){
						selected.setBorder(null);
					}
					
					selected = (Carta) entidade;
				}
			});
			
			this.add(aux);
		}
		
		seleção = new JButton();
		Image img = Importar.getBackground(BackgroundID.foice);
		seleção.setIcon(new ImageIcon(img));
		buttonCustomization(seleção);
		seleção.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inimigo.getBaralho().remover(selected);
				inimigo.getCemiterio().addCarta(selected);				
				
				LogPanel.appendText("A carta "+ selected.getNome()
						+" foi morta por Encapsulamento pelo "
						+jogador.getNome());
				selected.setBorder(null);
				
				jogador.allowEndTurn(true);
				frame.dispose();
			}
		});
		
		add(seleção);
		
		if(this.getComponents().length < 2){
			frame.dispose();
		}
	}
	
	public void buttonCustomization(JButton button){
		button.setForeground(Color.white);
		button.setBackground(new Color(62,28,100));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setFocusPainted(false);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = g.create();
		
		
		
		gr.clearRect(0, 0, HandFrame.WIDTH, HandFrame.HEIGHT);
		
		gr.drawImage(this.imagem, 0, 0,EncapsulamentoFrame.WIDTH,EncapsulamentoFrame.HEIGHT, null);
		
		
		gr.dispose();
		
	}
	
}















