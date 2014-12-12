package org.casaFinanceira.dao;

import java.util.Date;
import org.casaFinanceira.entidades.Financiamento;

public interface FinanciamentoDAO {
	public void insert(Financiamento financiamento);

	public void update(Financiamento financiamento);

	public boolean delete(Long id);

	public Financiamento findByID(Long id , Date data);



}
