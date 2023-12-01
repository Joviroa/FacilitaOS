package app.fos.controllers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import app.fos.exception.FOSException;
import app.fos.utils.InputUtils;
import ejb.fos.bo.EmpresaBO;
import ejb.fos.entidade.Empresa;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class EmpresaController extends BaseController{

	@EJB
	private EmpresaBO empresaBO;
	
	private Empresa empresa;
	
	
	@PostConstruct
	public void inicializar() {
		inicializarEmpresa();
	}
	
	
	private void inicializarEmpresa() {
		setEmpresa(empresaBO.buscarEmpresa());
		if(empresa == null){
			setEmpresa(new Empresa());
		}
	}


	public void salvarEmpresa() {
		try {
			validarEmpresa();
			empresaBO.salvarEmpresa(getEmpresa());
			System.out.println("Empresa salvo com sucesso.");
		} catch (FOSException e) {
			if(!e.getListaErros().isEmpty()) {
				this.setListaMensagemErro(e.getListaErros());
			}else {
				this.setMensagemErro(e.getMensagemErro());
			}
		}
	}
	
	private void validarEmpresa()  throws FOSException{
		FOSException fosException = new FOSException();
		fosException.instanciaListaErros();
		
		if(getEmpresa() == null) {
			return;
		}
		if(!InputUtils.validarString(getEmpresa().getNome())) {
			fosException.adicionarErroLista("O nome do empresa não é válido, está vazio ou contém números.");
		}
		
		if(getEmpresa().getEmail() == null || !InputUtils.validarEmail(getEmpresa().getEmail())) {
			fosException.adicionarErroLista("O email inserido não é válido. ");
		}
		
		if(getEmpresa().getTelefone() != null) {
			if(!InputUtils.validarTelefone(getEmpresa().getTelefone())) {
				fosException.adicionarErroLista("O telefone inserido é inválido.");
			}
			
		}
		
		if(!fosException.getListaErros().isEmpty()) {
			throw fosException;
		}
		
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
