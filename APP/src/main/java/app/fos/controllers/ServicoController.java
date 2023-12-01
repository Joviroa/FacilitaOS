package app.fos.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import app.fos.exception.FOSException;
import app.fos.utils.InputUtils;
import ejb.fos.bo.ServicoBO;
import ejb.fos.entidade.Servico;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ServicoController extends BaseController{

	@EJB
	private ServicoBO servicoBO;
	
	private Servico servicoSelecionado;
	private List<Servico> listaServicos = new ArrayList<Servico>();
	
	private Boolean ehEdicao;
	
	@PostConstruct
	public void inicializar() {
		inicializarListaServicos();
		inicializarBooleansTela();
	}
	
	
	public void salvarServico() {
		try {
			validarServico();
			servicoBO.salvarServico(getServicoSelecionado());
			redefinirServicoSelecionado();
			inicializarListaServicos();
			System.out.println("Servico salvo com sucesso.");
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
	
	public void removerServico(Servico servico) {
		try {
			servicoBO.excluirServico(servico);
			inicializarListaServicos();
			System.out.println("Serviço removido com sucesso.");
		} catch (Exception e) {
			this.setMensagemErro(e.getMessage());
		}finally {
			fechaStatusDialog();
		}
	}
	private void validarServico()  throws FOSException{
		FOSException fosException = new FOSException();
		fosException.instanciaListaErros();
		
		if(getServicoSelecionado() == null) {
			return;
		}
		if(getServicoSelecionado().getNome() == null || !InputUtils.validarString(getServicoSelecionado().getNome())) {
			fosException.adicionarErroLista("O nome inserido para o serviço é inválido. ");
		}
		if(getServicoSelecionado().getValor() == null || getServicoSelecionado().getValor().compareTo(BigDecimal.ZERO) == 0) {
			fosException.adicionarErroLista("O valor do serviço não pode ser nulo ou zero.");
		}
		if(!fosException.getListaErros().isEmpty()) {
			throw fosException;
		}
	}

	private void inicializarListaServicos() {
		setListaServicos(servicoBO.listarTodosServicos());
	}
	
	public void carregaServicoSelecioado(Servico servico) {
		setServicoSelecionado(servico);
		setEhEdicao(Boolean.TRUE);
	}
	
	public void redefinirServicoSelecionado() {
		setServicoSelecionado(new Servico());
	}
	
	public void fecharModal() {
		setEhEdicao(Boolean.FALSE);
		redefinirServicoSelecionado();
	}

	private void inicializarBooleansTela() {
		setEhEdicao(Boolean.FALSE);
	}


	public List<Servico> getListaServicos() {
		return listaServicos;
	}


	public void setListaServicos(List<Servico> listaServicos) {
		this.listaServicos = listaServicos;
	}


	public Servico getServicoSelecionado() {
		return servicoSelecionado;
	}


	public void setServicoSelecionado(Servico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}


	public ServicoBO getServicoBO() {
		return servicoBO;
	}


	public void setServicoBO(ServicoBO servicoBO) {
		this.servicoBO = servicoBO;
	}


	public Boolean getEhEdicao() {
		return ehEdicao;
	}


	public void setEhEdicao(Boolean ehEdicao) {
		this.ehEdicao = ehEdicao;
	}
	
}
