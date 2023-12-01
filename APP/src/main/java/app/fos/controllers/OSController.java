package app.fos.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ejb.fos.bo.OrdemServicoBO;
import ejb.fos.entidade.OrdemServico;
import ejb.fos.vo.OrdemServicoVO;

@SuppressWarnings("serial")
@Named(value = "oSController")
@ViewScoped
public class OSController extends BaseController {

	@EJB
	private OrdemServicoBO ordemServicoBO;
	private List<OrdemServico> listaOrdemServicos;
	
	private Boolean ehEdicao;
	
	@PostConstruct
	public void inicializar() {
		inicializarListaOrdemServicos();
		inicializarBooleansTela();
	}
	
	public void novaOS() {
		redirecionarDadosOrdemServico(null);
	}
	
	public void editarOS(OrdemServico os) {
		redirecionarDadosOrdemServico(os);
	}
	
	private void redirecionarDadosOrdemServico(OrdemServico ordemServico) {
		if(ordemServico != null) {
			this.setarObjetoNoFlash(ordemServico, "ordemServico");
			this.setarObjetoNoFlash(Boolean.TRUE, "modoEdicao");
		}else {
			this.setarObjetoNoFlash(Boolean.FALSE, "modoEdicao");
		}
		redireciona("/pages/dados_os.xhtml");
	}

	public void exibirOrdemServico(OrdemServico ordemServico){
		try {
			byte[] pdf = gerarRelatorio("/resources/relatorio/ordem_servico.jasper", preencherVORelatorio(ordemServico), new HashMap<String, Object>());
			this.exibirImagem(pdf, "os.pdf");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível exibir o pdf.");
		}
	}
	
	private List<OrdemServicoVO> preencherVORelatorio(OrdemServico os) {
		 List<OrdemServicoVO> listaVO = new ArrayList<OrdemServicoVO>();
		 listaVO.add(new OrdemServicoVO(os));
		 return listaVO;
	}
	
	public BigDecimal valorTotalOS(OrdemServico ordemServico) {
		return ordemServicoBO.obterValorTotalOS(ordemServico);
	}
	
	private void inicializarListaOrdemServicos() {
		setListaOrdemServicos(ordemServicoBO.listarOrdemServico());
	}
	
	private void inicializarBooleansTela() {
		setEhEdicao(Boolean.FALSE);
	}

	public List<OrdemServico> getListaOrdemServicos() {
		return listaOrdemServicos;
	}

	public void setListaOrdemServicos(List<OrdemServico> listaOrdemServicos) {
		this.listaOrdemServicos = listaOrdemServicos;
	}

	public Boolean getEhEdicao() {
		return ehEdicao;
	}

	public void setEhEdicao(Boolean ehEdicao) {
		this.ehEdicao = ehEdicao;
	}
}
