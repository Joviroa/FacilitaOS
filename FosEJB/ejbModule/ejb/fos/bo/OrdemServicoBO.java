package ejb.fos.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.fos.dao.OrdemServicoDAO;
import ejb.fos.entidade.OrdemServico;
import ejb.fos.entidade.Servico;
import ejb.fos.entidade.ServicoOs;

@Stateless
public class OrdemServicoBO {

	@EJB
	private OrdemServicoDAO ordemServicoDAO;

	public void salvarOrdemServico(OrdemServico ordemServico) {
		if (ordemServico.getId() == null) {
			ordemServicoDAO.persiste(ordemServico);
		} else {
			ordemServicoDAO.atualiza(ordemServico);
		}
	}

	public void excluirOrdemServico(OrdemServico ordemServico) {
		ordemServicoDAO.remove(ordemServico);
	}

	public List<OrdemServico> listarOrdemServico() {
		return ordemServicoDAO.buscaTodos(OrdemServico.class);
	}

	public List<Servico> obterListaServicos(OrdemServico ordemServicoSelecionado) {
		List<Servico> listaServico = new ArrayList<Servico>();
		if(ordemServicoSelecionado != null && ordemServicoSelecionado.getServicoOs() != null) {
			for(ServicoOs servicoOS : ordemServicoSelecionado.getServicoOs()) {
				listaServico.add(servicoOS.getServico());
			}
		}
		return listaServico;
	}
	
	public BigDecimal obterValorTotalOS(OrdemServico ordemServico) {
		BigDecimal valorTotalServico = new BigDecimal(0);
		for(ServicoOs sos : ordemServico.getServicoOs()) {
			valorTotalServico.add(sos.getServico().getValor());
		}
		return valorTotalServico;
	}

	public void salvarServicos(OrdemServico ordemServico, List<Servico> listaServicosSelecionados, Boolean modoEdicao) {
		if(modoEdicao) {
			for(Servico servico : listaServicosSelecionados) {
				if(!ordemServico.getServicoOs().contains(servico)) {
					ServicoOs servOS = new ServicoOs();
					servOS.setServico(servico);
					servOS.setOrdemServico(ordemServico);
				}
			}
		}
		
	}
	
}
