package Gráficos.SideFrames.handPanels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import entity.Carta;
import entity.Carta_Criatura;

public class DescriptionPanel {
	
	private JPanel panel;
	private JScrollPane scrollPanel;
	
	private JLabel nome;
	private JLabel ataque;
	private JLabel defesa;
	private JLabel skill;
	private Descricao descricao;
	
	public DescriptionPanel(){
		panel = new JPanel();
		scrollPanel = new JScrollPane(panel);
		
		Border titled = BorderFactory.createTitledBorder("Informações: ");
		Border innerBorder = BorderFactory
				.createTitledBorder(titled, "Informações: ", 0, 0, null, Color.white);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		

		scrollPanel.setBackground(new Color(0,0,0,0));
		panel.setBackground(new Color(0,0,0,100));
		
		scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		nome = new JLabel("Nome");
		ataque = new JLabel("Ataque");
		defesa = new JLabel("Defesa");
		skill = new JLabel("Skill");
		
		descricao = new Descricao();
		
				
		nome.setForeground(Color.white);
		skill.setForeground(Color.white);
		defesa.setForeground(Color.white);
		ataque.setForeground(Color.white);
		
		panel.add(nome);
		panel.add(ataque);
		panel.add(defesa);
		panel.add(skill);
		panel.add(descricao.getScroll());
				
	}
	
	
	public JPanel getPanel(){
		return this.panel;
	}
	public JScrollPane getScrollPanel(){
		return this.scrollPanel;
	}

	public void setInformacoes(Carta c){
		if(c != null){
			setNome(c.getNome());
			descricao.setDescricao(c.getDescricao());
		}else{
			setNome(null);
			descricao.setDescricao(null);
		}
		if(c instanceof Carta_Criatura){
			Carta_Criatura ccriatura = (Carta_Criatura)c;
			setAtaque(ccriatura.getAtaque()+"");
			setDefesa(ccriatura.getDefesa()+"");
			setSkill(ccriatura.getSkill()+"");
		}else{
			setAtaque(null);
			setDefesa(null);
			setSkill(null);
		}
		
	}

	public JLabel getNome() {
		return nome;
	}


	public void setNome(String nome) {
		if(nome == null){
			this.nome.setText(null);
		}else{
			this.nome.setText("Nome: "+nome);
		}
		
	}


	public JLabel getAtaque() {
		return ataque;
	}


	public void setAtaque(String ataque) {
		if(ataque == null){
			this.ataque.setText(null);
		}else{
			this.ataque.setText("Ataque: "+ ataque);
		}
	}


	public JLabel getDefesa() {
		return defesa;
	}


	public void setDefesa(String defesa) {
		if(defesa == null){
			this.defesa.setText(null);
		}else{
			this.defesa.setText("Defesa: "+ defesa);
		}
	}


	public JLabel getSkill() {
		return skill;
	}


	public void setSkill(String skill) {
		if(skill == null){
			this.skill.setText(null);
		}else{
			this.skill.setText("Skill: "+ skill);
		}
	}


	public JScrollPane getDescricao() {
		return descricao.getScroll();
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
class Descricao{
	private JTextArea descricao;
	private JScrollPane scroll;
	
	public Descricao(){
		descricao = new JTextArea("Descrição");
		scroll = new JScrollPane(descricao);
		
		descricao.setLineWrap(true);
		
		scroll.setBackground(new Color(0,0,0,0));
		descricao.setBackground(new Color(0,0,0,0));
		descricao.setForeground(Color.white);
		
		descricao.setEnabled(true);
		descricao.setEditable(false);
		
	}
	
	public JScrollPane getScroll(){
		return this.scroll;
	}
	public JTextArea getTextArea(){
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		if(descricao == null){
			this.descricao.setText(null);
		}else{
			this.descricao.setText("Descricao: "+descricao);
		}
		
	}
}









