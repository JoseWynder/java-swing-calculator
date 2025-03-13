package br.com.josewynder.calculadora.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
class Botao extends JButton {
	
	Botao(String texto, Color corBotao, Color corTexto, Font fonte) {
		setFont(fonte);
		setText(texto);
		setOpaque(true);
		setBackground(corBotao);
		setForeground(corTexto);
		setBorder(BorderFactory.createLineBorder(Teclado.CINZA_PADRAO, 1));
	}
}
