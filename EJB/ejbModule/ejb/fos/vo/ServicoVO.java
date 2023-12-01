package ejb.fos.vo;

import java.math.BigDecimal;

import ejb.fos.entidade.Servico;

public class ServicoVO {

	private String nomeServico;
	
	private BigDecimal valorServico;
	
	private String cnae;
	
	public ServicoVO(Servico servico) {
		if(servico == null) {
			System.out.println("Entidade nula passada ao construtor do ServicoVO.");
			return;
		}
		setNomeServico(servico.getNome());
		setCnae(servico.getCnae());
		setValorServico(servico.getValor());
	}

	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	
}
