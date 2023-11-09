package ejb.fos.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ejb.fos.entidade.Cliente;

@Stateless
public class ClienteDAO extends DAO{

	public Cliente buscarClientePorNome(String nome) {
		try {
			Query query = getEntityManager().createQuery(montaQueryBuscaClientePorNome());
			query.setParameter(":nome", nome);
			Cliente cliente = (Cliente)query.getSingleResult();
			return cliente;
		}catch(NoResultException e) {
			return null;
		}
	}

	private String montaQueryBuscaClientePorNome() {
		return  "SELECT cliente "
				+ "FROM Cliente cliente "
				+ "WHERE cliente.nome LIKE '%:nome%'";
	}
}
