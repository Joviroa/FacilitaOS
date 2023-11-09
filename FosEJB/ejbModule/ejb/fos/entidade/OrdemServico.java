package ejb.fos.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(schema="public", name="ordem_servico")
@Entity
public class OrdemServico implements Entidade{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="public.sequence_ordem_servico")
	@SequenceGenerator(name="public.sequence_ordem_servico", sequenceName="public.sequence_ordem_servico", allocationSize=1)
	@Column(name="id_os")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_cliente", nullable = true)
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_empresa", nullable = true)
	private Empresa empresa;
	
	@OneToMany(mappedBy = "ordemServico", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServicoOs> servicoOs;
	
	@Column(name="numero_os")
	private Integer numeroOS;
	
	@Column(name="colaborador")
	private String colaborador;
	
	@Column(name="descricao")
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_inicio")
	private Date dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_fim")
	private Date dataFim;
	
	@Column(name="pdf")
	private byte[] pdf;
	
	@Override
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<ServicoOs> getServicoOs() {
		return servicoOs;
	}

	public void setServicoOs(List<ServicoOs> servicoOs) {
		this.servicoOs = servicoOs;
	}

	public Integer getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(Integer numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getColaborador() {
		return colaborador;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}
	
	
}
