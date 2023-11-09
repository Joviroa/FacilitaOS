package ejb.fos.vo;

import ejb.fos.entidade.Servico;

public class ServicoVO {

	private String nome;
	
	private String valor;
	
	private String cnae;
	
	public ServicoVO(Servico servico) {
		if(servico == null) {
			System.out.println("Entidade nula passada ao construtor do ServicoVO.");
			return;
		}
		setNome(servico.getNome());
		setCnae(servico.getCnae());
		setValor(servico.getValor().toString());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}
	
}
