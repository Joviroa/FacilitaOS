package ejb.fos.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.fos.dao.OrdemServicoDAO;
import ejb.fos.dao.ServicoOsDAO;
import ejb.fos.entidade.OrdemServico;
import ejb.fos.entidade.Servico;
import ejb.fos.entidade.ServicoOs;

@Stateless
public class OrdemServicoBO {

	@EJB
	private OrdemServicoDAO ordemServicoDAO;
	
	@EJB
	private ServicoOsDAO servicoOsDAO;
	
	@EJB
	private EmpresaBO empresaBO;

	public void salvarOrdemServico(OrdemServico ordemServico, List<Servico> listaServicosSelecionados, Boolean modoEdicao) {
		salvarServicos(ordemServico, listaServicosSelecionados, modoEdicao);
		ordemServico.setNumeroOS(Integer.valueOf(ordemServicoDAO.getSequenceOS()));
		ordemServico.setEmpresa(empresaBO.buscarEmpresa());
		if (!modoEdicao) {
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
			valorTotalServico = valorTotalServico.add(sos.getServico().getValor());
		}
		return valorTotalServico;
	}

	private void salvarServicos(OrdemServico ordemServico, List<Servico> listaServicosSelecionados, Boolean modoEdicao) {
		if (listaServicosSelecionados != null) {
			if(ordemServico.getServicoOs() == null) {
				ordemServico.setServicoOs(new ArrayList<ServicoOs>());
			}
			for (Servico servico : listaServicosSelecionados) {
				if ((modoEdicao && !ordemServico.getServicoOs().contains(servico)) || !modoEdicao) {
					ServicoOs servOS = new ServicoOs();
					servOS.setServico(servico);
					servOS.setOrdemServico(ordemServico);
					ordemServico.getServicoOs().add(servOS);
				}
			}
		}
	}
}
