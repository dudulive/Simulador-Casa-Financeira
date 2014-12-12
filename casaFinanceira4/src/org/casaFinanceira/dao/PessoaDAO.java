package org.casaFinanceira.dao;

import org.casaFinanceira.entidades.Pessoa;

public interface PessoaDAO {

	public void insert(Pessoa pessoa);

	public void update(Pessoa pessoa);

	public boolean delete(Long id);

	public Pessoa findByID(Long id);

	
}
