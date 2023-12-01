package ejb.fos.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

@Stateless
public class OrdemServicoDAO extends DAO{

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String getSequenceOS() {
		Query query = getEntityManager().createNativeQuery("SELECT nextval('public.sequence_ordem_servico')");
		return query.getSingleResult().toString();
	}
}
