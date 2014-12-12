package org.casaFinanceira.entidades;

public class Pessoa {
	private Long id;
	private String nome;
	private String cpf;
	private double salarioLiquido;
	private double valorMaximoParcela;
	private String situacao;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the salarioLiquido
	 */
	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	/**
	 * @param salarioLiquido
	 *            the salarioLiquido to set
	 */
	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

	

	/**
	 * @return the situacao
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return the valorMaximoParcela
	 */
	public double getValorMaximoParcela() {
		return this.valorMaximoParcela;
	}

	/**
	 * @param valorMaximoParcela the valorMaximoParcela to set
	 */
	public void setValorMaximoParcela(double valorMaximoParcela) {
		this.valorMaximoParcela = valorMaximoParcela;
	}

}
