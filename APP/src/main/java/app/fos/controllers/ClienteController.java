package app.fos.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import app.fos.exception.FOSException;
import app.fos.utils.InputUtils;
import ejb.fos.bo.ClienteBO;
import ejb.fos.entidade.Cliente;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ClienteController extends BaseController{

	@EJB
	private ClienteBO clienteBO;
	
	private Cliente clienteSelecionado;
	private List<Cliente> listaClientes;
	
	private Boolean ehEdicao;
	
	@PostConstruct
	public void inicializar() {
		inicializarListaClientes();
		redefinirClienteSelecionado();
		inicializarBooleansTela();
	}
	
	
	public void salvarCliente() {
		try {
			validarCliente();
			clienteBO.salvarCliente(getClienteSelecionado());
			redefinirClienteSelecionado();
			inicializarListaClientes();
			System.out.println("Cliente salvo com sucesso.");
		} catch (FOSException e) {
			if(!e.getListaErros().isEmpty()) {
				this.setListaMensagemErro(e.getListaErros());
			}else {
				this.setMensagemErro(e.getMensagemErro());
			}
		}finally {
			fechaStatusDialog();
		}
	}
	
	public void excluirCliente(Cliente cliente) {
		try {
			clienteBO.excluirCliente(cliente);
			inicializarListaClientes();
			System.out.println("Cliente removido com sucesso.");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			fechaStatusDialog();
		}
	}
	public void carregaClienteSelecioado(Cliente cliente) {
		setClienteSelecionado(cliente);
		setEhEdicao(Boolean.TRUE);
	}
	
	public void redefinirClienteSelecionado() {
		setClienteSelecionado(new Cliente());
		setEhEdicao(Boolean.FALSE);
	}
	
	private void inicializarListaClientes() {
		setListaClientes(clienteBO.listarTodosClientes());
	}
	
	public void fecharModal() {
		setEhEdicao(Boolean.FALSE);
		redefinirClienteSelecionado();
	}
	
	private void validarCliente()  throws FOSException{
		FOSException fosException = new FOSException();
		fosException.instanciaListaErros();
		
		if(getClienteSelecionado() == null) {
			return;
		}
		if(!InputUtils.validarString(getClienteSelecionado().getNome())) {
			fosException.adicionarErroLista("O nome do cliente não é válido, está vazio ou contém números.");
		}
		
		if(getClienteSelecionado().getEmail() == null || !InputUtils.validarEmail(getClienteSelecionado().getEmail())) {
			fosException.adicionarErroLista("O email inserido não é válido. ");
		}
		
		if(getClienteSelecionado().getTelefone() != null) {
			if(!InputUtils.validarTelefone(getClienteSelecionado().getTelefone())) {
				fosException.adicionarErroLista("O telefone inserido é inválido.");
			}
			
		}
		
		if(getClienteSelecionado().getCep() == null || getClienteSelecionado().getCep().isEmpty()) {
			fosException.adicionarErroLista("O cep do cliente � inv�lido.");
		}
		
		if(!fosException.getListaErros().isEmpty()) {
			throw fosException;
		}
		
	}
	
	private void inicializarBooleansTela() {
		setEhEdicao(Boolean.FALSE);
	}
	
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Boolean getEhEdicao() {
		return ehEdicao;
	}

	public void setEhEdicao(Boolean ehEdicao) {
		this.ehEdicao = ehEdicao;
	}
	
}
