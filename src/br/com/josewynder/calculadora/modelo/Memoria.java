package br.com.josewynder.calculadora.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando {
		CLEAR, CANCEL_ENTRY, BACK_SPACE,
		DIV, MULT, SUB, SOMA, IGUAL, VIRGULA, SINAL, NUMERO;
	}
	
	private static final Memoria instancia = new Memoria();
	private final List<MemoriaObserver> observers = new ArrayList<>();
	
	private TipoComando ultimaOperacao = null;
	private TipoComando ultimoComando = null;
	private boolean substituir = true;
	
	private String textoPainelP = "0";
	private String textoBufferP = "0";
	
	private String textoBufferS = "";
	private String textoPainelS = "";
	
	private Memoria() {}
	
	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void registrarObserver(MemoriaObserver observer) {
		observers.add(observer);
	}
	
	public String getTextoPainelP() {
		return String.format("%.14s", separarCasa(textoPainelP));
	}
	
	public String getTextoPainelS() {
		String a; String b;
		
		a = textoPainelS.trim().length() >= 10 ? String.format("%.7s... %s", textoPainelS, textoPainelS.substring(textoPainelS.length() - 2))
				: textoPainelS;
		b = textoBufferS.trim().length() >= 10 ? String.format("%.8s... %s", textoBufferS, textoBufferS.substring(textoBufferS.length() - 2))
				: textoBufferS;
		
		return (a + b).endsWith("÷ 0 =") ? "Proibido ÷ 0" : a + b;
	}
	
	private String separarCasa(String num) {
		String inteiro = num.contains(",") ? num.substring(0, num.indexOf(",")) : num;
	    String decimal = num.contains(",") ? num.substring(num.indexOf(",")) : "";
	    boolean sinal = inteiro.startsWith("-");
	    if(sinal) inteiro = inteiro.substring(1);
	    
	    if(decimal.contains("E")) {
	    	String aux1 = String.format("%.9s", decimal.substring(0, decimal.indexOf("E")));
	    	String aux2 = decimal.substring(decimal.indexOf("E"), decimal.length());
    		decimal = aux1 + aux2;
	    } else {
	    	decimal = String.format("%.9s", decimal);
	    }
		
	    int rest = inteiro.length() % 3;
	    int quant = inteiro.length() / 3;

	    StringBuilder formatado = new StringBuilder();
	    
	    if (rest > 0) {
	        formatado.append(inteiro.substring(0, rest));
	        if (quant > 0) {
	            formatado.append(".");
	        }
	    }

	    for (int i = 0; i < quant; i++) {
	        formatado.append(inteiro.substring(rest + i * 3, rest + (i + 1) * 3));
	        if (i < quant - 1) {
	            formatado.append(".");
	        }
	    }
	    
	    formatado.append(decimal);

	    return sinal ? "-" + formatado.toString() : formatado.toString();
	}
	
	private Double passarStringDouble(String str) {
		return Double.parseDouble(str.replace(",", "."));
	}
	
	private boolean verificarIgualZero(String str) {
		return passarStringDouble(str) == 0;
	}
	
	private void controlarBackSpace() {
		if(substituir) return;
		if((textoPainelP.startsWith("-")) && (textoPainelP.length() == 2 ||
			(textoPainelP.length() == 3 && verificarIgualZero(textoPainelP)))) {
				textoPainelP = "0";
				substituir = true;
		} else if(textoPainelP.length() > 1) {
			textoPainelP = textoPainelP.substring(0, textoPainelP.length() - 1);
			substituir = false;
		} else {
			textoPainelP = "0";
			substituir = true;
		}
	}
	
	public void processarComando(String texto) {
		
		if(texto == "0" && textoPainelP == "0") return;
		
		TipoComando comando = detectarTipoComando(texto);
		
		if(comando == TipoComando.CLEAR) {
			textoPainelP = "0";
			textoBufferP = "0";
			textoBufferS = "";
			textoPainelS = "";
			ultimaOperacao = null;
			ultimoComando = null;
			substituir = true;
		} else if(comando == TipoComando.CANCEL_ENTRY) {
			textoPainelP = "0";
			substituir = true;
			if(!textoPainelS.isEmpty() && !textoBufferS.isEmpty()) {
				textoPainelS = "";
				textoBufferS = "";
			}
		} else if(comando == TipoComando.BACK_SPACE) {
			controlarBackSpace();
		} else if(comando == TipoComando.VIRGULA) {
			if(substituir) textoPainelP = "0";
			if(textoPainelP.length() >= 10 && !substituir) return;
			if(!textoPainelP.contains(",")) textoPainelP = textoPainelP + texto;
			substituir = false;
		} else if(comando == TipoComando.SINAL) {
			if(verificarIgualZero(textoPainelP) && !textoPainelP.contains("-")) return;
			textoPainelP = textoPainelP.contains("-") ? textoPainelP.substring(1) : "-" + textoPainelP;
		} else if(comando == TipoComando.NUMERO) {
			if(textoPainelP.length() >= 10 && !substituir) return;
			textoPainelP = substituir ? texto : textoPainelP + texto;
			substituir = false;
		} else {
			textoBufferS = comando == TipoComando.IGUAL && ultimoComando == TipoComando.NUMERO
					? " " + textoPainelP + " =" : "";
			textoPainelP = resultadoOperacao();
			textoBufferP = textoPainelP;
			if(textoBufferS.isEmpty()) {
				textoPainelS = textoBufferP + " " + texto;
			} else if(ultimaOperacao == TipoComando.IGUAL){
				textoPainelS = "";
			}
			ultimaOperacao = comando;
			substituir = true;
		}
		ultimoComando = comando;
        
		observers.forEach(obs -> obs.valorAlterado(getTextoPainelP(), getTextoPainelS()));
	}
	
	private String resultadoOperacao() {
		if(substituir && ultimoComando != TipoComando.CANCEL_ENTRY) return textoPainelP;
		if(verificarIgualZero(textoPainelP) && verificarIgualZero(textoBufferP)) return "0";

		Double numeroAtual = passarStringDouble(textoPainelP);
		Double numeroBuffer = passarStringDouble(textoBufferP);
		Double resultado = numeroAtual;
		
		if(ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		} else if(ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		} else if(ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		} else if(ultimaOperacao == TipoComando.DIV) {
			if(numeroAtual == 0.0) return "0";
			resultado = numeroBuffer / numeroAtual;
		}
		
		String texto = resultado.toString().replace(".", ",");
		return texto.endsWith(",0") ? texto.replace(",0", "") : texto;
	}
	
	private TipoComando detectarTipoComando(String comando) {
		
		if("C" == comando)
			return TipoComando.CLEAR;
		if("CE" == comando)
			return TipoComando.CANCEL_ENTRY;
		if("backSpace" == comando)
			return TipoComando.BACK_SPACE;
		if("÷" == comando)
			return TipoComando.DIV;
		if("*" == comando)
			return TipoComando.MULT;
		if("-" == comando)
			return TipoComando.SUB;
		if("+" == comando)
			return TipoComando.SOMA;
		if("=" == comando)
			return TipoComando.IGUAL;
		if("," == comando)
			return TipoComando.VIRGULA;
		if("±" == comando)
			return TipoComando.SINAL;
		
		return TipoComando.NUMERO;
	}
	
}
