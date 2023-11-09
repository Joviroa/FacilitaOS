package ejb.fos.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(schema="public", name="servico_os")
@Entity
public class ServicoOs implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.sequence_servico_os")
	@SequenceGenerator(name = "public.sequence_servico_os", sequenceName = "public.sequence_servico_os", allocationSize = 1)
	@Column(name = "id_servico_os")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_servico", nullable = true)
	private Servico servico;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_os", nullable = true)
	private OrdemServico ordemServico;

	@Override
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServicoOs other = (ServicoOs) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
