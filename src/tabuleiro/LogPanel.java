package tabuleiro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.AncestorListener;

public class LogPanel extends JPanel{
	private static JTextArea textArea;
	private static JScrollPane scrollPanel;
	
	public LogPanel(){
		
		textArea = new JTextArea("Histórico: ");
		scrollPanel = new JScrollPane(textArea);
		
		this.setBackground(new Color(0,0,0,0));
		scrollPanel.setBackground(new Color(0,0,0,0));
		
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);
		textArea.setCaretColor(Color.white);
		textArea.setSelectionColor(Color.red);
		textArea.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				e.consume();
			}
		});
		
		scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		textArea.setLineWrap(true);
		
		this.setLayout(new BorderLayout());
		this.add(scrollPanel,BorderLayout.CENTER);
		LogPanel.appendText("------------------- Fim do turno "+1+" -------------------");
	}
	public static void setFontColor(Color color){
		textArea.setForeground(color);
	}
	public static void resetColor(){
		textArea.setForeground(Color.white);
	}
	
	public static void setText(String text){
		textArea.setText(text);
	}
	public static void appendText(String text){
		textArea.append("\n" + text);
		resetColor();
	}
	
}
