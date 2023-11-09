package ejb.fos.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(schema="public", name="empresa")
@Entity
public class Empresa implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.sequence_empresa")
	@SequenceGenerator(name = "public.sequence_empresa", sequenceName = "public.sequence_empresa", allocationSize = 1)
	@Column(name = "id_empresa")
	private Long id;

	@Column(name = "nome", nullable=false)
	private String nome;
	
	@Column(name = "cnpj", nullable=false)
	private String cnpj;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "eh_unipessoal")
	private Boolean ehUnipessoal;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "logo")
	private byte[] logo;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getEhUnipessoal() {
		return ehUnipessoal;
	}

	public void setEhUnipessoal(Boolean ehUnipessoal) {
		this.ehUnipessoal = ehUnipessoal;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
