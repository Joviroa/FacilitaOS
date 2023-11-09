package app.fos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import app.fos.exception.FOSException;
import app.fos.utils.InputUtils;
import app.fos.utils.JSFUtils;
import ejb.fos.bo.ClienteBO;
import ejb.fos.bo.OrdemServicoBO;
import ejb.fos.bo.ServicoBO;
import ejb.fos.entidade.Cliente;
import ejb.fos.entidade.OrdemServico;
import ejb.fos.entidade.Servico;

@SuppressWarnings("serial")
@Named(value="dadosOsController")
@ViewScoped
public class DadosOSController extends BaseController {

	@EJB
	private OrdemServicoBO ordemServicoBO;
	@EJB
	private ServicoBO servicoBO;
	@EJB
	private ClienteBO clienteBO;
	
	
	private OrdemServico ordemServico;
	private List<Servico> listaServico;
	private List<Servico> listaServicosSelecionados;
	private List<Cliente> listaCliente;
	
	private Boolean modoEdicao;
	
	@PostConstruct
	private void inicializar() {
		obterDadosFlash();
		inicializarListas();
	}


	private void inicializarListas() {
		setListaServico(servicoBO.listarTodosServicos());
		setListaCliente(clienteBO.listarTodosClientes());
	}

	private void obterDadosFlash() {
		if(JSFUtils.get("modoEdicao") != null) {
			setModoEdicao((Boolean)JSFUtils.get("modoEdicao"));
		}else {
			setModoEdicao(Boolean.FALSE);
		}
		if(JSFUtils.get("ordemServico") != null) {
			setOrdemServico((OrdemServico) JSFUtils.get("ordemServico"));
		}else {
			setOrdemServico(new OrdemServico());
		}
	}

	public void salvarOrdemServico() {
		try {
			validarOrdemServico();
			ordemServicoBO.salvarOrdemServico(getOrdemServico());
			System.out.println("OrdemServico salva com sucesso.");
		} catch (FOSException e) {
			if(!e.getListaErros().isEmpty()) {
				this.setListaMensagemErro(e.getListaErros());
			}else {
				this.setMensagemErro(e.getMensagemErro());
			}
		}
	}
	
	public void voltar() {
		redireciona("/pages/os.xhtml");
	}

	public List<Cliente> completeCliente(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Cliente> allClientes = clienteBO.listarTodosClientes();
        return allClientes.stream().filter(c -> c.getNome().toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }
	
	public void inicializarDadosModal() {
		setListaServico(servicoBO.listarTodosServicos());
	}
	
	public void fecharModal() {
	}
	
	public void salvarModalServicos() {
		ordemServicoBO.salvarServicos(getOrdemServico(), listaServicosSelecionados, modoEdicao);
	}
	
	private void validarOrdemServico()  throws FOSException{
		FOSException fosException = new FOSException();
		fosException.instanciaListaErros();
		
		if(getOrdemServico() == null) {
			return;
		}
		if(getOrdemServico().getCliente() != null) {
			if(getOrdemServico().getCliente().getNome() == null || !InputUtils.validarString(getOrdemServico().getCliente().getNome())) {
				fosException.adicionarErroLista("O nome do cliente está inv�lido ou vazio.");
			}
			if(getOrdemServico().getCliente().getEmail() == null || !InputUtils.validarEmail(getOrdemServico().getCliente().getEmail())) {
				fosException.adicionarErroLista("O email do cliente está inv�lido ou vazio.");
			}
			if(getOrdemServico().getCliente().getTelefone() == null || !InputUtils.validarString(getOrdemServico().getCliente().getTelefone())) {
				fosException.adicionarErroLista("O telefone do cliente está inválido ou vazio.");
			}
		}else {
			fosException.adicionarErroLista("É necessário escolher um cliente para a ordem de serviço.");
		}
		
		if(getOrdemServico().getServicoOs() == null || getOrdemServico().getServicoOs().isEmpty()) {
			fosException.adicionarErroLista("É necessário escolher no mínimo um serviço para a ordem de serviço.");
		}
		
		if(!fosException.getListaErros().isEmpty()) {
			throw fosException;
		}
	}
	
	public OrdemServicoBO getOrdemServicoBO() {
		return ordemServicoBO;
	}

	public void setOrdemServicoBO(OrdemServicoBO ordemServicoBO) {
		this.ordemServicoBO = ordemServicoBO;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public List<Servico> getListaServico() {
		return listaServico;
	}

	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
	}

	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Boolean getModoEdicao() {
		return modoEdicao;
	}

	public void setModoEdicao(Boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}

	public List<Servico> getListaServicosSelecionados() {
		return listaServicosSelecionados;
	}

	public void setListaServicosSelecionados(List<Servico> listaServicosSelecionados) {
		this.listaServicosSelecionados = listaServicosSelecionados;
	}
	
}
