package tabuleiro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

@SuppressWarnings("serial")
public class LogPanel extends JPanel{
	private static JTextArea textArea;
	private static JScrollPane scrollPanel;
	
	public LogPanel(){
		
		textArea = new JTextArea("Histórico: ");
		scrollPanel = new JScrollPane(textArea);
		
		this.setBackground(new Color(0,0,0,0));
		scrollPanel.setBackground(new Color(0,0,0,0));
		
		textArea.setBackground(new Color(0,0,0));
		textArea.setForeground(Color.white);
		textArea.setCaretColor(Color.white);
		textArea.setSelectionColor(Color.cyan);
		
		textArea.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				e.consume();
			}
		});
		
		scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		textArea.setLineWrap(true);
		
		this.setLayout(new BorderLayout());
		this.add(scrollPanel,BorderLayout.CENTER);
	}
		
	public static void setText(String text){
		textArea.setText(text);
	}
	public static void appendText(String text){
		textArea.append("\n" + text);
		
		scrollPanel.getViewport().setViewPosition(new Point(0,textArea.getSize().height));
	}
	
	
}
