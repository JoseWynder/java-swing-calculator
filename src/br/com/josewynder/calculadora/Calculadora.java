package br.com.josewynder.calculadora;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import br.com.josewynder.calculadora.visao.Display;
import br.com.josewynder.calculadora.visao.Teclado;

@SuppressWarnings("serial")
public class Calculadora extends JFrame {
	
	private Calculadora() {
		setTitle(" Calculadora");
		setSize(336, 452);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
            BufferedImage icon = ImageIO.read(Calculadora.class.getResource
            		("/br/com/josewynder/calculadora/resources/images/calculadora.png"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
		organizarLayout();
		setVisible(true);
	}
	
	private void organizarLayout() {
		final Display display = new Display();
		add(display, BorderLayout.PAGE_START);
		
		final Teclado teclado = new Teclado();
		add(teclado, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		new Calculadora();
	}
}
