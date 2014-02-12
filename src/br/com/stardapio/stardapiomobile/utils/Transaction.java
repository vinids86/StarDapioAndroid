package br.com.stardapio.stardapiomobile.utils;

public interface Transaction {
	public void execute() throws Exception;
	public void updateView();
}
