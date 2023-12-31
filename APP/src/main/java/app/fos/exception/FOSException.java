package app.fos.exception;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class FOSException extends Exception {
	
	private String mensagemErro;
	private List<String> listaErros;
	
	public FOSException() {
		
	} 
	public FOSException(String mensagemErro) {
		setMensagemErro(mensagemErro);
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}
	
	public void instanciaListaErros() {
		this.listaErros = new ArrayList<String>();
	}
	
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	public List<String> getListaErros() {
		return listaErros;
	}
	public void setListaErros(List<String> listaErros) {
		this.listaErros = listaErros;
	}
	public void adicionarErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	public void adicionarErroLista(String mensagemErro) {
		this.listaErros.add(mensagemErro);
	}
}
