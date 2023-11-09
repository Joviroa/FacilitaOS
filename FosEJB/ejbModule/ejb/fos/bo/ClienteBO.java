package ejb.fos.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.fos.dao.ClienteDAO;
import ejb.fos.entidade.Cliente;

@Stateless
public class ClienteBO {

	public ClienteBO() {
	}
	
	@EJB
	private ClienteDAO clienteDAO;

	public void salvarCliente(Cliente cliente) {
		if(cliente.getId() == null) {
			clienteDAO.persiste(cliente);
		}else {
			clienteDAO.atualiza(cliente);
		}
	}
	
	public void excluirCliente(Cliente cliente) {
		clienteDAO.remove(cliente);
	}
	
	public Cliente buscarClientePorNome(String nome) {
		return clienteDAO.buscarClientePorNome(nome);
	}

	public List<Cliente> listarTodosClientes() {
		return clienteDAO.buscaTodos(Cliente.class);
	}
	
	
}
