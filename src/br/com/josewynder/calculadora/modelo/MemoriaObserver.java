package br.com.josewynder.calculadora.modelo;

@FunctionalInterface
public interface MemoriaObserver {

	void valorAlterado(String novoValor1, String novoValor2);
}