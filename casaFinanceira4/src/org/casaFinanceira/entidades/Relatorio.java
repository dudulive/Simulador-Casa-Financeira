package org.casaFinanceira.entidades;

import java.util.Date;

public class Relatorio {

	private Long id;
	private String nome;
	private String cpf;
	private Double salarioLiquido;
	private Double valorMaximoParcela;
	private String situacao;
	private Double valorFinanciado;
	private Double taxaJuros;
	private int periodos;
	private String tipo;
	private Date dataCadastro;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @param nome the nome to set
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
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * @return the salarioLiquido
	 */
	public Double getSalarioLiquido() {
		return salarioLiquido;
	}
	/**
	 * @param salarioLiquido the salarioLiquido to set
	 */
	public void setSalarioLiquido(Double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
	/**
	 * @return the valorMaximoParcela
	 */
	public Double getValorMaximoParcela() {
		return valorMaximoParcela;
	}
	/**
	 * @param valorMaximoParcela the valorMaximoParcela to set
	 */
	public void setValorMaximoParcela(Double valorMaximoParcela) {
		this.valorMaximoParcela = valorMaximoParcela;
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
	 * @return the valorFinanciado
	 */
	public Double getValorFinanciado() {
		return valorFinanciado;
	}
	/**
	 * @param valorFinanciado the valorFinanciado to set
	 */
	public void setValorFinanciado(Double valorFinanciado) {
		this.valorFinanciado = valorFinanciado;
	}
	/**
	 * @return the taxaJuros
	 */
	public Double getTaxaJuros() {
		return taxaJuros;
	}
	/**
	 * @param taxaJuros the taxaJuros to set
	 */
	public void setTaxaJuros(Double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}
	/**
	 * @return the periodos
	 */
	public int getPeriodos() {
		return periodos;
	}
	/**
	 * @param periodos the periodos to set
	 */
	public void setPeriodos(int periodos) {
		this.periodos = periodos;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}
	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
