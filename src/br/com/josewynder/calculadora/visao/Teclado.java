package br.com.josewynder.calculadora.visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import br.com.josewynder.calculadora.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {

	private static final Font FONTE_MAIOR = new Font("Courier New", Font.PLAIN, 22);
	private static final Font FONTE_PADRAO = new Font("Arial", Font.PLAIN, 18);
	private static final Font FONTE_MENOR = new Font("Serif", Font.PLAIN, 16);
	private static final Color OPERACOES = new Color(238, 238, 238);
	private static final Color BRANCO = Color.WHITE;
	private static final Color PRETO = Color.BLACK;
	static final Color CINZA_PADRAO = new Color(245, 245, 245);
	
	public Teclado() {
		
		setBackground(CINZA_PADRAO);
		setLayout(new GridLayout(5,4));
			// Organizando em uma grade 5x4
		
		criarBotao("C", OPERACOES, PRETO, FONTE_MENOR);
		criarBotao("CE", OPERACOES, PRETO, FONTE_MENOR);
		criarBackSpace(null, OPERACOES, PRETO, FONTE_MAIOR);
		criarBotao("÷", OPERACOES, PRETO, new Font("Courier New", Font.PLAIN, 20));
		
		criarBotao("7", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("8", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("9", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("X", OPERACOES, PRETO, new Font("Courier New", Font.PLAIN, 16));
		
		criarBotao("4", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("5", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("6", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("-", OPERACOES, PRETO, FONTE_MAIOR);
		
		criarBotao("1", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("2", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("3", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("+", OPERACOES, PRETO, FONTE_MAIOR);
		
		criarBotao("±", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao("0", BRANCO, PRETO, FONTE_PADRAO);
		criarBotao(",", BRANCO, PRETO, FONTE_MAIOR);
		criarBotao("=", new Color(0, 90, 158), BRANCO, FONTE_MAIOR);
	}
	
	private void criarBotao(String texto, Color corBotao, Color corTexto, Font fonte) {
		Botao botao = new Botao(texto, corBotao, corTexto, fonte);
		if(botao.getText() == "X") botao.setActionCommand("*");
		adicionarBotao(botao);
	}
	
	private void criarBackSpace(String texto, Color corBotao, Color corTexto, Font fonte) {
		ImageIcon icon = new ImageIcon
				(Teclado.class.getResource("/br/com/josewynder/calculadora/resources/images/backSpace.png"));
		Image image = icon.getImage().getScaledInstance(21, 21, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		
		Botao botao = new Botao(texto, corBotao, corTexto, fonte);
		botao.setIcon(icon);
		botao.setActionCommand("backSpace");
		adicionarBotao(botao);
	}
	
	private void adicionarBotao(Botao botao) {
		botao.addActionListener(this);
		add(botao);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Memoria.getInstancia().processarComando(e.getActionCommand());
	}
}
