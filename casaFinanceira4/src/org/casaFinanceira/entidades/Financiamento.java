package org.casaFinanceira.entidades;

import java.util.Date;

public class Financiamento {
	private Long id;
	private Double valorFinanciado;
	private Double taxaJuros;
	private int periodos;
	private String tipo;
	private Date dataCadastro;
	private Integer idPessoa;

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
	 * @return the periodos
	 */
	public int getPeriodos() {
		return periodos;
	}

	/**
	 * @param periodos
	 *            the periodos to set
	 */
	public void setPeriodos(int periodos) {
		this.periodos = periodos;
	}

	/**
	 * @return the juros
	 */
	/**
	 * @return the valorFinanciado
	 */
	public Double getValorFinanciado() {
		return valorFinanciado;
	}

	/**
	 * @param valorFinanciado
	 *            the valorFinanciado to set
	 */
	public void setValorFinanciado(Double valorFinanciado) {
		this.valorFinanciado = valorFinanciado;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the taxaJuros
	 */
	public Double getTaxaJuros() {
		return taxaJuros;
	}

	/**
	 * @param taxaJuros
	 *            the taxaJuros to set
	 */
	public void setTaxaJuros(Double taxaJuros) {
		this.taxaJuros = taxaJuros;
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
