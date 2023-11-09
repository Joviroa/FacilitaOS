package ejb.fos.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.fos.dao.ServicoDAO;
import ejb.fos.entidade.Servico;

@Stateless
public class ServicoBO {

	@EJB
	private ServicoDAO servicoDAO;

	public void salvarServico(Servico servico) {
		if (servico.getId() == null) {
			servicoDAO.persiste(servico);
		} else {
			servicoDAO.atualiza(servico);
		}
	}

	public void excluirServico(Servico servico) {
		servicoDAO.remove(servico);
	}

	public List<Servico> listarTodosServicos() {
		return servicoDAO.buscaTodos(Servico.class);
	}

}
