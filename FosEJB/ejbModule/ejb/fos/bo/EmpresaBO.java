package ejb.fos.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.fos.dao.EmpresaDAO;
import ejb.fos.entidade.Empresa;

@Stateless
public class EmpresaBO {

	@EJB
	private EmpresaDAO EmpresaDAO;

	public void salvarEmpresa(Empresa Empresa) {
		if (Empresa.getId() == null) {
			EmpresaDAO.persiste(Empresa);
		} else {
			EmpresaDAO.atualiza(Empresa);
		}
	}

	public Empresa buscarEmpresa() {
		return EmpresaDAO.busca(Empresa.class, 1L);
	}
	
	public void excluirEmpresa(Empresa Empresa) {
		EmpresaDAO.remove(Empresa);
	}

	public List<Empresa> listarTodosEmpresa() {
		return EmpresaDAO.buscaTodos(Empresa.class);
	}
}
