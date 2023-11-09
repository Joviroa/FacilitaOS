package ejb.fos.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ejb.fos.entidade.OrdemServico;
import ejb.fos.entidade.ServicoOs;

public class OrdemServicoVO {

	private String nomeCliente;
	
	private String cepCliente;
	
	private String telefoneCliente;
	
	private String emailCliente;
	
	private String nomeEmpresa;
	
	private String unipessoalEmpresa;
	
	private String cnpjEmpresa;
	
	private String telefoneEmpresa;
	
	private String emailEmpresa;

	private String enderecoEmpresa;
	
	private String descricaoEmpresa;
	
	private String dataInicio;
	
	private String dataFim;
	
	private Integer quantidadeServicosOS;
	
	private BigDecimal valorTotalServicos;
	
	private List<ServicoVO> listaServicos;
	
	
	
	
	public OrdemServicoVO(OrdemServico os) {
		if(os == null || os.getServicoOs() == null || os.getServicoOs().isEmpty()) {
			System.out.println("Entidade nula passada ao construtor do VO.");
			return;
		}
		setNomeCliente(os.getCliente().getNome());
		if(os.getCliente().getTelefone() != null) {
			setTelefoneCliente(os.getCliente().getTelefone());
		}
		setEmailCliente(os.getCliente().getEmail());
		setCepCliente(os.getCliente().getCep());
		setNomeEmpresa(os.getEmpresa().getNome());
		setCnpjEmpresa(os.getEmpresa().getCnpj());
		setTelefoneEmpresa(os.getEmpresa().getTelefone());
		setEmailEmpresa(os.getEmpresa().getEmail());
		setUnipessoalEmpresa(os.getEmpresa().getEhUnipessoal() ? "Sim" : "Não");
		setEnderecoEmpresa(os.getEmpresa().getEndereco());
		setDescricaoEmpresa(os.getEmpresa().getDescricao());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		setDataInicio(sdf.format(os.getDataInicio().toString()));
		setDataFim(sdf.format(os.getDataFim().toString()));
		
		setListaServicos(new ArrayList<ServicoVO>());
		int quantidadeServicoOS = 0;
		BigDecimal valorTotalServicos = new BigDecimal(0);
		for(ServicoOs sos : os.getServicoOs()) {
			getListaServicos().add(new ServicoVO(sos.getServico()));
			quantidadeServicoOS++;
			valorTotalServicos.add(sos.getServico().getValor());
		}
		setQuantidadeServicosOS(quantidadeServicoOS);
		setValorTotalServicos(valorTotalServicos);
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getTelefoneEmpresa() {
		return telefoneEmpresa;
	}

	public void setTelefoneEmpresa(String telefoneEmpresa) {
		this.telefoneEmpresa = telefoneEmpresa;
	}

	public String getEmailEmpresa() {
		return emailEmpresa;
	}

	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(String cepCliente) {
		this.cepCliente = cepCliente;
	}

	public String getUnipessoalEmpresa() {
		return unipessoalEmpresa;
	}

	public void setUnipessoalEmpresa(String unipessoalEmpresa) {
		this.unipessoalEmpresa = unipessoalEmpresa;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public Integer getQuantidadeServicosOS() {
		return quantidadeServicosOS;
	}

	public void setQuantidadeServicosOS(Integer quantidadeServicosOS) {
		this.quantidadeServicosOS = quantidadeServicosOS;
	}

	public BigDecimal getValorTotalServicos() {
		return valorTotalServicos;
	}

	public void setValorTotalServicos(BigDecimal valorTotalServicos) {
		this.valorTotalServicos = valorTotalServicos;
	}

	public List<ServicoVO> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<ServicoVO> listaServicos) {
		this.listaServicos = listaServicos;
	}

	public String getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(String enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}
	
}
