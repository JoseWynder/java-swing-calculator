package br.com.josewynder.calculadora.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.josewynder.calculadora.modelo.Memoria;
import br.com.josewynder.calculadora.modelo.MemoriaObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObserver {
	
	public Display() {
		Memoria.getInstancia().registrarObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new PainelSecundario());
		add(new PainelPrincipal());
	}
	
	private class PainelSecundario extends JPanel {
		
		private static JLabel label;
		
		private PainelSecundario() {
			setLayout(new FlowLayout(FlowLayout.RIGHT, 22, 18));
			setBackground(Teclado.CINZA_PADRAO);
			setPreferredSize(new Dimension(336, 40));
			
			label = new JLabel();
			label.setForeground(Color.GRAY);
			label.setFont(new Font("SansSerif", Font.PLAIN, 14));
			add(label);
		}
	}
	
	private class PainelPrincipal extends JPanel {
		
		private static JLabel label;

		private PainelPrincipal() {
			setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
			setBackground(Teclado.CINZA_PADRAO);
			setPreferredSize(new Dimension(336, 60));
			
			label = new JLabel(Memoria.getInstancia().getTextoPainelP());
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Impact", Font.PLAIN, 42));
			add(label);
		}
	}
	
	@Override
	public void valorAlterado(String novoValor1, String novoValor2) {
		PainelPrincipal.label.setText(novoValor1);
		PainelSecundario.label.setText(novoValor2);
	}
}
